package trainer.api.backend.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import trainer.api.backend.model.entity.Informe;

import java.util.List;

public interface IInformeDao extends CrudRepository<Informe, Long> {
    @Query("FROM Informe i WHERE i.objetivoId=:id")
    List<Informe> findListByObjetivoId(@Param("id") Long id);
}
