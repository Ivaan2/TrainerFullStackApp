package trainer.api.backend.service;

import trainer.api.backend.model.entity.Comida;

public interface IComida {

    public Comida save(Comida comida);

    public Comida findById(Long id);

    public void delete(Comida comida);
}
