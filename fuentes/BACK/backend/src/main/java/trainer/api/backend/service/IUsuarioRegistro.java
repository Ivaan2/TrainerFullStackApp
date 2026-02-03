package trainer.api.backend.service;

import trainer.api.backend.model.dto.UsuarioRegistroDTO;

import java.util.List;

public interface IUsuarioRegistro {

    UsuarioRegistroDTO save(UsuarioRegistroDTO usuarioRegistro);

    //Buscar un usuario por Id
    UsuarioRegistroDTO findById(Long id);

    //Eliminar el usuario
    void delete(UsuarioRegistroDTO usuarioRegistro);

    List<UsuarioRegistroDTO> findAll();

    UsuarioRegistroDTO findByEmail(String email);

    // Login/registro usando idToken de Google
    UsuarioRegistroDTO googleLogin(String idToken);
}
