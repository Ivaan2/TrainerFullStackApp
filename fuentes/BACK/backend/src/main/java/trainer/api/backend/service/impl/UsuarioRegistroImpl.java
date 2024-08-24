package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainer.api.backend.model.dao.IUsuarioRegistroDao;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.service.IUsuarioRegistro;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioRegistroImpl implements IUsuarioRegistro {

    private final IUsuarioRegistroDao usuarioRegistroDao;

    @Transactional
    @Override
    public UsuarioRegistro save(UsuarioRegistro usuarioRegistro) {
        return usuarioRegistroDao.save(usuarioRegistro);
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioRegistro findById(Integer id) {
        return usuarioRegistroDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(UsuarioRegistro usuarioRegistro) {
        usuarioRegistroDao.delete(usuarioRegistro);
    }

    @Transactional
    @Override
    public List<UsuarioRegistro> findAll() {
        return (List<UsuarioRegistro>) usuarioRegistroDao.findAll();
    }

    @Override
    public UsuarioRegistro findByEmail(String email) {
        return usuarioRegistroDao.findByEmail(email);
    }
}
