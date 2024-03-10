package trainer.api.backend.service;

import trainer.api.backend.model.entity.UsuarioRegistro;

public interface IUsuarioRegistro {

    /*
     * Crear y actualizar el registro
     */
    public UsuarioRegistro save(UsuarioRegistro usuarioRegistro);

    //Buscar un usuario por Id
    public UsuarioRegistro findById(Long id);

    //Eliminar el usuario
    public void delete(UsuarioRegistro usuarioRegistro);
}
