package trainer.api.backend.service;

import trainer.api.backend.model.entity.Comida;

import java.util.List;

public interface IComida {

    public Comida save(Comida comidaDto);

    public Comida findById(Long id);

    public void delete(Comida comida);

    public List<Comida> findList(Long DietaId);
}
