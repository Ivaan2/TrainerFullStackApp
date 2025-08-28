package trainer.api.backend.service;

import trainer.api.backend.model.dto.ComidaDTO;
import trainer.api.backend.model.entity.Comida;

import java.util.List;

public interface IComida {

    ComidaDTO save(ComidaDTO comidaDto);

    ComidaDTO findById(Long id);

    void delete(ComidaDTO comida);

    List<ComidaDTO> findList(Long DietaId);
}
