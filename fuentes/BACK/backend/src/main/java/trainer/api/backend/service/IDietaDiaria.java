package trainer.api.backend.service;

import trainer.api.backend.model.dto.DietaDiariaDTO;
import trainer.api.backend.model.entity.DietaDiaria;

public interface IDietaDiaria {

    DietaDiariaDTO save(DietaDiariaDTO dieta);

    DietaDiariaDTO findById(Long id);

    void delete(DietaDiariaDTO dieta);
}
