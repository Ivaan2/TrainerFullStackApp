package trainer.api.backend.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import trainer.api.backend.model.entity.UsuarioRegistro;

public interface IUsuarioRegistroDao extends CrudRepository<UsuarioRegistro, Integer> {

    @Query("SELECT u FROM UsuarioRegistro u WHERE u.email = ?1")
    UsuarioRegistro findByEmail(String email);
}
