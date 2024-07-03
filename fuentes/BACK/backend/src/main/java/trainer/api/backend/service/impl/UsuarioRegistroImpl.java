package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainer.api.backend.model.dao.IUsuarioRegistroDao;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.service.IUsuarioRegistro;

@Service
@AllArgsConstructor
public class UsuarioRegistroImpl implements IUsuarioRegistro {

    IUsuarioRegistroDao usuarioRegistroDao;

    @Transactional
    @Override
    public UsuarioRegistro save(UsuarioRegistro usuarioRegistro) {
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
