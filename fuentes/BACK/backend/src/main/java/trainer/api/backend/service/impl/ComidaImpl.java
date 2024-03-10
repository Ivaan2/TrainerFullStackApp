package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainer.api.backend.model.dao.IComidaDao;
import trainer.api.backend.model.entity.Comida;
import trainer.api.backend.service.IComida;

@Service
@AllArgsConstructor
public class ComidaImpl implements IComida{

    public IComidaDao comidaDao;

    @Transactional
    @Override
    public Comida save(Comida comida) {
        return comidaDao.save(comida);
    }

    @Transactional
    @Override
    public Comida findById(Long id) {
        return comidaDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Comida comida) {
        comidaDao.delete(comida);
    }
}
