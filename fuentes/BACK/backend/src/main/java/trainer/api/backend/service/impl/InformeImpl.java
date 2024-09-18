package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trainer.api.backend.model.dao.IInformeDao;
import trainer.api.backend.model.dto.InformeDTO;
import trainer.api.backend.model.entity.Informe;
import trainer.api.backend.model.entity.enums.NivelActividad;
import trainer.api.backend.service.IInforme;

import java.util.List;

@Service
@AllArgsConstructor
public class InformeImpl implements IInforme {

    public IInformeDao informeDao;

    @Override
    public Informe save(InformeDTO informeDto) {
        Double peso = informeDto.getPeso();
        int altura = informeDto.getAltura();
        Informe informe = Informe.builder()
                .altura(informeDto.getAltura())
                .edad(informeDto.getEdad())
                .abdomen(informeDto.getAbdomen())
                .imc(calculateIMC(peso, altura))
                .cadera(informeDto.getCadera())
                .biceps(informeDto.getBiceps())
                .cuadriceps(informeDto.getCuadriceps())
                .gemelos(informeDto.getGemelos())
                .fechaRegistro(informeDto.getFechaRegistro())
                .pecho(informeDto.getPecho())
                .peso(informeDto.getPeso())
                .gluteos(informeDto.getGluteos())
                .hombros(informeDto.getHombros())
                .antebrazo(informeDto.getAntebrazo())
                .diasEntreno(informeDto.getDiasEntreno())
                .seguimientoDieta(informeDto.getSeguimientoDieta())
                .tmb(calculateTMB(peso, altura, informeDto.getEdad(), informeDto.getSexo().name(), informeDto.getDiasEntreno()))
                .id(informeDto.getId())
                .sexo(informeDto.getSexo())
                .nivelActividad(informeDto.getNivelActividad())
                .objetivoId(informeDto.getObjetivoId())
                .porcentajeGraso(informeDto.getPorcentajeGraso())
                .porcentajeMusculo(informeDto.getPorcentajeMusculo()).build();
        return informeDao.save(informe);
    }

    // Tasa Metabolica Basal
    private static Double calculateTMB(Double peso, int altura, int edad, String sexo, int diasEntreno) {
        double tmbAprox = (10 * peso) + (6.25 * altura) - (5 * edad) + (sexo.equals("MASCULINO") ? 5 : -161);
        double nivelActividad;
        if (diasEntreno == 0) {
            nivelActividad = 1.2;

        } else if (diasEntreno < 4) {
            nivelActividad = 1.375;

        } else if (diasEntreno < 6) {
            nivelActividad = 1.55;

        } else if (diasEntreno < 8) {
            nivelActividad = 1.725;

        } else {
            nivelActividad = 1.9;
        }
        return tmbAprox * nivelActividad;
    }

    private static Double calculateIMC(Double peso, int altura) {
        return peso / Math.pow(altura / 100.0, 2);
    }

    @Override
    public Informe findById(Long id) {
        return informeDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Informe informe) {
        informeDao.delete(informe);
    }

    @Override
    public List<Informe> findListByIdObjetivo(Long id) {
        return informeDao.findListByObjetivoId(id);
    }
}
