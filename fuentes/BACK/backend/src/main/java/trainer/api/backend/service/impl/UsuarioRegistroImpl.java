package trainer.api.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import trainer.api.backend.config.mapper.MapperFactory;
import trainer.api.backend.model.dao.IUsuarioRegistroDao;
import trainer.api.backend.model.dto.UsuarioRegistroDTO;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.service.IUsuarioRegistro;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UsuarioRegistroImpl implements IUsuarioRegistro {

    private final MapperFactory mapperFactory;

    private final IUsuarioRegistroDao usuarioRegistroDao;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${google.client.id:}")
    private String googleClientId;

//    @Transactional
    @Override
    public UsuarioRegistroDTO save(UsuarioRegistroDTO usuarioRegistroDto) {
        UsuarioRegistro usuarioRegistro = mapperFactory.getUsuarioRegistroMapper().toEntity(usuarioRegistroDto);
        UsuarioRegistro saved = usuarioRegistroDao.save(usuarioRegistro);
        return mapperFactory.getUsuarioRegistroMapper().toDTO(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioRegistroDTO findById(Long id) {
        return usuarioRegistroDao.findById(id).map(mapperFactory.getUsuarioRegistroMapper()::toDTO)
                .orElse(null);
    }

    @Transactional
    @Override
    public void delete(UsuarioRegistroDTO usuarioRegistroDto) {
        // Mapear DTO a Entity antes de eliminar
        UsuarioRegistro entity = mapperFactory.getUsuarioRegistroMapper().toEntity(usuarioRegistroDto);
        usuarioRegistroDao.delete(entity);
    }

    //@Transactional(readOnly = true)
    @Override
    public List<UsuarioRegistroDTO> findAll() {
        Iterable<UsuarioRegistro> usuarios = usuarioRegistroDao.findAll();
        return StreamSupport.stream(usuarios.spliterator(), false)
                .map(mapperFactory.getUsuarioRegistroMapper()::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public UsuarioRegistroDTO findByEmail(String email) {
        UsuarioRegistro entity = usuarioRegistroDao.findByEmail(email);
        return entity != null ? mapperFactory.getUsuarioRegistroMapper().toDTO(entity) : null;
    }

    @Override
    @Transactional
    public UsuarioRegistroDTO googleLogin(String idToken) {
        if (ObjectUtils.isEmpty(idToken)) return null;

        String tokenInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> tokenInfo;
        try {
            tokenInfo = restTemplate.getForObject(tokenInfoUrl, Map.class);
        } catch (RestClientException ex) {
            return null;
        }

        if (ObjectUtils.isEmpty(tokenInfo) || ObjectUtils.isEmpty(tokenInfo.get("email"))) {
            return null;
        }

        // validar aud si googleClientId está configurado
        Object audObj = tokenInfo.get("aud");
        if (ObjectUtils.isNotEmpty(googleClientId) && ObjectUtils.isNotEmpty(audObj)) {
            String aud = audObj.toString();
            if (!googleClientId.equals(aud)) {
                return null;
            }
        }

        String email = tokenInfo.get("email").toString();
        String nombre = tokenInfo.containsKey("name") ? tokenInfo.get("name").toString() : "";
        String apellido = tokenInfo.containsKey("family_name") ? tokenInfo.get("family_name").toString() : "";
        String picture = tokenInfo.containsKey("picture") ? tokenInfo.get("picture").toString() : null;

        UsuarioRegistroDTO usuarioExistente = findByEmail(email);
        if (ObjectUtils.isNotEmpty(usuarioExistente)) {
            // ocultar contraseña antes de devolver
            usuarioExistente.setPassword(null);
            return usuarioExistente;
        }

        UsuarioRegistroDTO nuevoUsuario = UsuarioRegistroDTO.builder()
                .email(email)
                .nombre(nombre)
                .apellido1(apellido)
                .nombreUsuario(email.contains("@") ? email.substring(0, email.indexOf('@')) : email)
                .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                .rutaAvatar(picture)
                .build();

        UsuarioRegistroDTO guardado = save(nuevoUsuario);
        guardado.setPassword(null);
        return guardado;
    }
}
