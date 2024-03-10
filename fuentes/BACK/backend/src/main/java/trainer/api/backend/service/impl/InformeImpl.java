package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trainer.api.backend.model.dao.IInformeDao;
import trainer.api.backend.model.entity.Informe;
import trainer.api.backend.service.IInforme;

@Service
@AllArgsConstructor
public class InformeImpl implements IInforme {

    public IInformeDao informeDao;

    @Override
    public Informe save(Informe informe) {
        return informeDao.save(informe);
    }

    @Override
    public Informe findById(Long id) {
        return informeDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Informe informe) {
        informeDao.delete(informe);
    }
}
