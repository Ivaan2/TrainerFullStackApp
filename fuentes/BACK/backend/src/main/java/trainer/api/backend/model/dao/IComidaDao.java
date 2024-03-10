package trainer.api.backend.model.dao;

import org.springframework.data.repository.CrudRepository;
import trainer.api.backend.model.entity.Comida;

public interface IComidaDao extends CrudRepository<Comida, Long> {
}
