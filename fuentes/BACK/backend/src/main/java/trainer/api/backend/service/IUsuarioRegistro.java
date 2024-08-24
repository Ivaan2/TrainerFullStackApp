package trainer.api.backend.service;

import trainer.api.backend.model.entity.UsuarioRegistro;

import java.util.List;

public interface IUsuarioRegistro {

    public UsuarioRegistro save(UsuarioRegistro usuarioRegistro);

    //Buscar un usuario por Id
    public UsuarioRegistro findById(Integer id);

    //Eliminar el usuario
    public void delete(UsuarioRegistro usuarioRegistro);

    List<UsuarioRegistro> findAll();

    UsuarioRegistro findByEmail(String email);
}
