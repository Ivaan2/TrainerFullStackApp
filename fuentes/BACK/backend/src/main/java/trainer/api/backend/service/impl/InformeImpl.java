package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trainer.api.backend.model.dao.IInformeDao;
import trainer.api.backend.model.dto.InformeDTO;
import trainer.api.backend.model.entity.Informe;
import trainer.api.backend.service.IInforme;

@Service
@AllArgsConstructor
public class InformeImpl implements IInforme {

    public IInformeDao informeDao;

    @Override
    public Informe save(InformeDTO informeDto) {
        Informe informe = Informe.builder()
                .altura(informeDto.getAltura())
                .edad(informeDto.getEdad())
                .abdomen(informeDto.getAbdomen())
                .imc(informeDto.getImc())
                .cadera(informeDto.getCadera())
                .biceps(informeDto.getBiceps())
                .cuadriceps(informeDto.getCuadriceps())
                .gemelos(informeDto.getGemelos())
                .fechaRegistro(informeDto.getFechaRegistro())
                .pecho(informeDto.getPecho())
                .peso(informeDto.getPeso())
                .gluteos(informeDto.getGluteos())
                .tmb(informeDto.getTmb())
                .id(informeDto.getId())
                .sexo(informeDto.getSexo())
                .hombros(informeDto.getHombros())
                .nivelActividad(informeDto.getNivelActividad())
                .objetivoId(informeDto.getObjetivoId())
                .porcentajeGraso(informeDto.getPorcentajeGraso())
                .porcentajeMusculo(informeDto.getPorcentajeMusculo()).build();
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
