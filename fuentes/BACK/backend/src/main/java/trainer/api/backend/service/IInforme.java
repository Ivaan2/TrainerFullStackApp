package trainer.api.backend.service;

import trainer.api.backend.model.dto.InformeDTO;

import java.util.List;

public interface IInforme {

    InformeDTO save(InformeDTO informeDto);

    InformeDTO findById(Long id);

    void delete(InformeDTO informe);

    List<InformeDTO> findListByIdObjetivo(Long id);
}
