package trainer.api.backend.service;

import trainer.api.backend.model.dto.UsuarioRegistroDTO;
import trainer.api.backend.model.entity.UsuarioRegistro;

public interface IUsuarioRegistro {

    public UsuarioRegistro save(UsuarioRegistroDTO usuarioRegistro);

    //Buscar un usuario por Id
    public UsuarioRegistro findById(Long id);

    //Eliminar el usuario
    public void delete(UsuarioRegistro usuarioRegistro);
}
