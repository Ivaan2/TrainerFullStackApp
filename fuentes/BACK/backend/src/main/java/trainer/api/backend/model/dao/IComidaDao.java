package trainer.api.backend.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import trainer.api.backend.model.entity.Comida;

import java.util.List;

public interface IComidaDao extends CrudRepository<Comida, Long> {

    /*
    Mostrar lista de comidas según idDieta
     */
    @Query("SELECT c FROM Comida c WHERE c.dietaDiaria.id = :idDieta ORDER BY c.hora ASC")
    List<Comida> findByIdDieta(Long idDieta);
}
