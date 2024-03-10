package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trainer.api.backend.model.dao.IObjetivoDao;
import trainer.api.backend.model.entity.Objetivo;
import trainer.api.backend.service.IObjetivo;

@Service
@AllArgsConstructor
public class ObjetivoImpl implements IObjetivo {

    public IObjetivoDao objetivoDao;

    @Override
    public Objetivo save(Objetivo objetivo) {
        return objetivoDao.save(objetivo);
    }

    @Override
    public Objetivo findById(Long id) {
        return objetivoDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Objetivo objetivo) {
        objetivoDao.delete(objetivo);
    }
}
