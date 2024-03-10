package trainer.api.backend.model.dao;

import org.springframework.data.repository.CrudRepository;
import trainer.api.backend.model.entity.Informe;

public interface IInformeDao extends CrudRepository<Informe, Long> {
}
