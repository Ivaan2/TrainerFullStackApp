package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainer.api.backend.config.mapper.MapperFactory;
import trainer.api.backend.model.dao.IComidaDao;
import trainer.api.backend.model.dto.ComidaDTO;
import trainer.api.backend.model.entity.Comida;
import trainer.api.backend.service.IComida;

import java.util.List;

@Service
@AllArgsConstructor
public class ComidaImpl implements IComida{

    public IComidaDao comidaDao;
    private final MapperFactory mapperFactory;

    @Transactional
    @Override
    public ComidaDTO save(ComidaDTO comidaDto) {
        // Convertir DTO a entidad usando el mapper
        Comida comida = mapperFactory.getComidaMapper().toEntity(comidaDto);
        Comida saved = comidaDao.save(comida);
        // Convertir de vuelta a DTO
        return mapperFactory.getComidaMapper().toDTO(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public ComidaDTO findById(Long id) {
        return comidaDao.findById(id)
                .map(mapperFactory.getComidaMapper()::toDTO)
                .orElse(null);
    }

    @Transactional
    @Override
    public void delete(ComidaDTO comidaDto) {
        Comida comida = mapperFactory.getComidaMapper().toEntity(comidaDto);
        comidaDao.delete(comida);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ComidaDTO> findList(Long dietaId) {
        List<Comida> listaComida = comidaDao.findByIdDieta(dietaId);
        if (listaComida.isEmpty()) {
            return List.of(); // Retornamos lista vacía en lugar de null
        }
        return listaComida.stream()
                .map(mapperFactory.getComidaMapper()::toDTO)
                .toList();
    }
}
