package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trainer.api.backend.model.dao.IInformeDao;
import trainer.api.backend.model.dao.IObjetivoDao;
import trainer.api.backend.model.dao.IUsuarioRegistroDao;
import trainer.api.backend.model.dto.InformeDTO;
import trainer.api.backend.model.entity.Informe;
import trainer.api.backend.model.entity.Objetivo;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.model.entity.enums.NivelActividad;
import trainer.api.backend.model.entity.enums.Sexo;
import trainer.api.backend.service.IInforme;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InformeImpl implements IInforme {

    public IInformeDao informeDao;
    private IObjetivoDao objetivoDaoService;
    private IUsuarioRegistroDao usuarioRegistroDaoService;

    @Override
    public Informe save(InformeDTO informeDto) {
        Optional<Objetivo> objetivo = objetivoDaoService.findById(informeDto.getObjetivoId());
        var objetivoObj = objetivo.orElse(null);
        UsuarioRegistro usuario = usuarioRegistroDaoService.findById(Math.toIntExact(objetivoObj.getUsuarioId())).get();

        int edad = usuario.getEdad();
        Sexo sexo = usuario.getSexo();
        Double peso = informeDto.getPeso();
        int altura = informeDto.getAltura();
        Informe informe = Informe.builder()
                .altura(altura)
                .abdomen(informeDto.getAbdomen())
                .imc(calculateIMC(peso, altura))
                .cadera(informeDto.getCadera())
                .biceps(informeDto.getBiceps())
                .cuadriceps(informeDto.getCuadriceps())
                .gemelos(informeDto.getGemelos())
                .sexo(sexo)
                .edad(edad)
                .fechaRegistro(dateToTimestamp(informeDto.getFechaRegistro()))
                .pecho(informeDto.getPecho())
                .peso(peso)
                .gluteos(informeDto.getGluteos())
                .hombros(informeDto.getHombros())
                .antebrazo(informeDto.getAntebrazo())
                .diasEntreno(informeDto.getDiasEntreno())
                .seguimientoDieta(informeDto.getSeguimientoDieta())
                .id(informeDto.getId())
                .tmb(calculateTMB(peso, altura, edad, sexo.toString(), informeDto.getDiasEntreno()))
                .nivelActividad(informeDto.getNivelActividad())
                .objetivoId(informeDto.getObjetivoId())
                .porcentajeGraso(informeDto.getPorcentajeGraso())
                .porcentajeMusculo(informeDto.getPorcentajeMusculo()).build();
        return informeDao.save(informe);
    }

    private static Timestamp dateToTimestamp(String fecha) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDate localDate = LocalDate.parse(fecha, dtf);
        LocalDateTime localDateTime = localDate.atStartOfDay();

        // Convertir LocalDateTime a Timestamp
        return Timestamp.valueOf(localDateTime);
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
