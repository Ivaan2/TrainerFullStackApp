package trainer.api.backend.model.dao;

import org.springframework.data.repository.CrudRepository;
import trainer.api.backend.model.entity.Objetivo;

public interface IObjetivoDao extends CrudRepository<Objetivo, Long> {
}
