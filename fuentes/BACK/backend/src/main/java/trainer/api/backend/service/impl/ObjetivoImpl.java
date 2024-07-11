package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainer.api.backend.model.dao.IObjetivoDao;
import trainer.api.backend.model.dto.ObjetivoDTO;
import trainer.api.backend.model.entity.Objetivo;
import trainer.api.backend.service.IObjetivo;

import java.util.List;

@Service
@AllArgsConstructor
public class ObjetivoImpl implements IObjetivo {

    @Autowired
    public IObjetivoDao objetivoDao;

    @Override
    public List<Objetivo> findListByUserId(Long id) {
        return objetivoDao.findListByUserId(id);
    }

    @Override
    public Objetivo save(ObjetivoDTO objetivoDto) {
        Objetivo objetivo = Objetivo.builder()
                .cumplido(objetivoDto.getCumplido())
                .descripcion(objetivoDto.getDescripcion())
                .fechaFin(objetivoDto.getFechaFin())
                .fechaRegistro(objetivoDto.getFechaRegistro())
                .id(objetivoDto.getId())
                .usuarioId(objetivoDto.getUsuarioId())
                .build();
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

    @Override
    public boolean existsById(Long id) {
        return objetivoDao.existsById(id);
    }
}
