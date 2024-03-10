package trainer.api.backend.model.dao;

import org.springframework.data.repository.CrudRepository;
import trainer.api.backend.model.entity.UsuarioRegistro;

public interface IUsuarioRegistroDao extends CrudRepository<UsuarioRegistro, Long> {
}
