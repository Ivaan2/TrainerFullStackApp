package trainer.api.backend.model.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import trainer.api.backend.model.entity.Objetivo;

import java.util.List;

public interface IObjetivoDao extends CrudRepository<Objetivo, Long> {
    @Query("FROM Objetivo o WHERE o.usuario.idUsuarioRegistro=:id")
    List<Objetivo> findListByUserId(@Param("id") Long id);

    // Find last objective by user id
    @Query("FROM Objetivo o WHERE o.usuario.idUsuarioRegistro=:id ORDER BY o.id DESC")
    List<Objetivo> findLastByUserId(@Param("id") Long id, Pageable pageable);

}
