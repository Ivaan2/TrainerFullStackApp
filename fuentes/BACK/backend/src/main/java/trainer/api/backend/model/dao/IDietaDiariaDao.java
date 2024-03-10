package trainer.api.backend.model.dao;

import org.springframework.data.repository.CrudRepository;
import trainer.api.backend.model.entity.DietaDiaria;

public interface IDietaDiariaDao extends CrudRepository<DietaDiaria, Long> {
}
