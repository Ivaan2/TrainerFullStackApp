package trainer.api.backend.service;

import trainer.api.backend.model.entity.Informe;

public interface IInforme {

    public Informe save(Informe informe);

    public Informe findById(Long id);

    public void delete(Informe informe);
}
