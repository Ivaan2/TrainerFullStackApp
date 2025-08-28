package trainer.api.backend.service;

import trainer.api.backend.model.dto.UsuarioRegistroDTO;
import trainer.api.backend.model.entity.UsuarioRegistro;

import java.util.List;

public interface IUsuarioRegistro {

    UsuarioRegistroDTO save(UsuarioRegistroDTO usuarioRegistro);

    //Buscar un usuario por Id
    UsuarioRegistroDTO findById(Long id);

    //Eliminar el usuario
    void delete(UsuarioRegistroDTO usuarioRegistro);

    List<UsuarioRegistroDTO> findAll();

    UsuarioRegistroDTO findByEmail(String email);
}
