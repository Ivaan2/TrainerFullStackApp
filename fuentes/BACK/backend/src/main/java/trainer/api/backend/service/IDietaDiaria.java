package trainer.api.backend.service;

import trainer.api.backend.model.dto.DietaDiariaDTO;
import trainer.api.backend.model.entity.DietaDiaria;

public interface IDietaDiaria {

    public DietaDiaria save(DietaDiariaDTO dieta);

    public DietaDiaria findById(Long id);

    public void delete(DietaDiaria dieta);
}
