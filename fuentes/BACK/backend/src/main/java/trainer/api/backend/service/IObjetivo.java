package trainer.api.backend.service;

import trainer.api.backend.model.dto.ObjetivoDTO;
import trainer.api.backend.model.entity.Objetivo;

public interface IObjetivo {

    public Objetivo save(ObjetivoDTO objetivoDto);

    public Objetivo findById(Long id);

    public void delete(Objetivo objetivo);

    public boolean existsById(Long id);
}
