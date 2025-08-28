package trainer.api.backend.service;

import trainer.api.backend.model.dto.ObjetivoDTO;
import trainer.api.backend.model.entity.Objetivo;

import java.util.List;

public interface IObjetivo {

    ObjetivoDTO findLastByUserId(Long id);

    public ObjetivoDTO save(ObjetivoDTO objetivoDto);

    public ObjetivoDTO findById(Long id);

    public void delete(ObjetivoDTO objetivo);

    public boolean existsById(Long id);

    public List<ObjetivoDTO> findListByUserId(Long id);
}
