package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainer.api.backend.config.mapper.MapperFactory;
import trainer.api.backend.model.dao.IUsuarioRegistroDao;
import trainer.api.backend.model.dto.UsuarioRegistroDTO;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.service.IUsuarioRegistro;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class UsuarioRegistroImpl implements IUsuarioRegistro {

    private final MapperFactory mapperFactory;

    private final IUsuarioRegistroDao usuarioRegistroDao;

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
}
