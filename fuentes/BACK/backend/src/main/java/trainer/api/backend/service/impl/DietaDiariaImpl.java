package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trainer.api.backend.model.dao.IDietaDiariaDao;
import trainer.api.backend.model.entity.DietaDiaria;
import trainer.api.backend.service.IDietaDiaria;

@Service
@AllArgsConstructor
public class DietaDiariaImpl implements IDietaDiaria {

    public IDietaDiariaDao dietaDiariaDao;

    @Override
    public DietaDiaria save(DietaDiaria dieta) {
        return dietaDiariaDao.save(dieta);
    }

    @Override
    public DietaDiaria findById(Long id) {
        return dietaDiariaDao.findById(id).orElse(null);
    }

    @Override
    public void delete(DietaDiaria dieta) {
        dietaDiariaDao.delete(dieta);
    }
}
