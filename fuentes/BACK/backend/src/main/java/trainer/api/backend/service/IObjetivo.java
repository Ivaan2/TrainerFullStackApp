package trainer.api.backend.service;

import trainer.api.backend.model.entity.Objetivo;

public interface IObjetivo {

    public Objetivo save(Objetivo objetivo);

    public Objetivo findById(Long id);

    public void delete(Objetivo objetivo);
}
