package trainer.api.backend.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainer.api.backend.model.dao.IUsuarioRegistroDao;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.service.IUsuarioRegistro;

@Service
public class UsuarioRegistroImpl implements IUsuarioRegistro {

    @Autowired
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
