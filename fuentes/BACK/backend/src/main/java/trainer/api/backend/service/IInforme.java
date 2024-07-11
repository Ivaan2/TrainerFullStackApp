package trainer.api.backend.service;

import trainer.api.backend.model.dto.InformeDTO;
import trainer.api.backend.model.entity.Informe;

import java.util.List;

public interface IInforme {

    public Informe save(InformeDTO informeDto);

    public Informe findById(Long id);

    public void delete(Informe informe);

    public List<Informe> findListByIdObjetivo(Long id);
}
