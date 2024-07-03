package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainer.api.backend.model.dao.IComidaDao;
import trainer.api.backend.model.entity.Comida;
import trainer.api.backend.service.IComida;

import java.util.List;

@Service
@AllArgsConstructor
public class ComidaImpl implements IComida{

    public IComidaDao comidaDao;

    @Transactional
    @Override
    public Comida save(Comida comida) {
//        var comidaObj = Comida.builder()
//                .aporteCalorico(comidaDto.getAporteCalorico())
//                .hora(comidaDto.getHora())
//                .aporteCarbohidratos(comidaDto.getAporteCarbohidratos())
//                .aporteGrasa(comidaDto.getAporteGrasa())
//                .aporteProteico(comidaDto.getAporteProteico())
//                .nombre(comidaDto.getNombre())
//                .dietaDiaria(comidaDto.getDietaDiaria())
//                .build();
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

    @Transactional
    @Override
    public List<Comida> findList(Long idDieta) {
        List<Comida> listaComida = comidaDao.findByIdDieta(idDieta);
        if (listaComida.isEmpty()){
            return null;
        }
        return listaComida;
    }
}
