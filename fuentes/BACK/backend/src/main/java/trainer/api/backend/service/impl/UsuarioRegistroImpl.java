package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainer.api.backend.model.dao.IUsuarioRegistroDao;
import trainer.api.backend.model.dto.UsuarioRegistroDTO;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.service.IUsuarioRegistro;

@Service
@AllArgsConstructor
public class UsuarioRegistroImpl implements IUsuarioRegistro {

    IUsuarioRegistroDao usuarioRegistroDao;

    @Transactional
    @Override
    //TODO: Aplicar el empleo de DTO's a todas las tablas
    public UsuarioRegistro save(UsuarioRegistroDTO usuarioRegistroDto) {
        var usuarioRegistro = UsuarioRegistro.builder()
                .idUsuarioRegistro(usuarioRegistroDto.getIdUsuarioRegistro())
                .apellido1(usuarioRegistroDto.getApellido1())
                .apellido2(usuarioRegistroDto.getApellido2())
                .email(usuarioRegistroDto.getEmail())
                .fechaRegistro(usuarioRegistroDto.getFechaRegistro())
                .build();
        return usuarioRegistroDao.save(usuarioRegistro);
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioRegistro findById(Long id) {
        return usuarioRegistroDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(UsuarioRegistro usuarioRegistro) {
        usuarioRegistroDao.delete(usuarioRegistro);
    }
}
