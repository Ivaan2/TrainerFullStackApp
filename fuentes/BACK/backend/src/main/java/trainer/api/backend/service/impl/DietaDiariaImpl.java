package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trainer.api.backend.config.mapper.MapperFactory;
import trainer.api.backend.model.dao.IDietaDiariaDao;
import trainer.api.backend.model.dto.DietaDiariaDTO;
import trainer.api.backend.model.entity.DietaDiaria;
import trainer.api.backend.service.IDietaDiaria;

@Service
@AllArgsConstructor
public class DietaDiariaImpl implements IDietaDiaria {

    public IDietaDiariaDao dietaDiariaDao;
    private final MapperFactory mapperFactory;

    @Override
    public DietaDiariaDTO save(DietaDiariaDTO dietaDto) {
        // Convertir DTO a entidad usando el mapper
        DietaDiaria dieta = mapperFactory.getDietaDiariaMapper().toEntity(dietaDto);
        DietaDiaria saved = dietaDiariaDao.save(dieta);
        // Convertir de vuelta a DTO antes de retornar
        return mapperFactory.getDietaDiariaMapper().toDTO(saved);
    }

    @Override
    public DietaDiariaDTO findById(Long id) {
        return dietaDiariaDao.findById(id)
                .map(mapperFactory.getDietaDiariaMapper()::toDTO)
                .orElse(null);
    }

    @Override
    public void delete(DietaDiariaDTO dietaDto) {
        // Convertimos el DTO a entidad para poder eliminarlo
        DietaDiaria dieta = mapperFactory.getDietaDiariaMapper().toEntity(dietaDto);
        dietaDiariaDao.delete(dieta);
    }
}
