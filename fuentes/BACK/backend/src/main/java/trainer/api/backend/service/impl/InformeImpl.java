package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trainer.api.backend.config.mapper.MapperFactory;
import trainer.api.backend.model.dao.IInformeDao;
import trainer.api.backend.model.dao.IObjetivoDao;
import trainer.api.backend.model.dao.IUsuarioRegistroDao;
import trainer.api.backend.model.dto.InformeDTO;
import trainer.api.backend.model.entity.Informe;
import trainer.api.backend.model.entity.Objetivo;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.model.entity.enums.Sexo;
import trainer.api.backend.service.IInforme;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class InformeImpl implements IInforme {

    private final IInformeDao informeDaoService;
    private final IObjetivoDao objetivoDaoService;
    private final IUsuarioRegistroDao usuarioRegistroDaoService;
    private final MapperFactory mapperFactory;

    @Override
    public InformeDTO save(InformeDTO informeDto) {
        Objetivo objetivo = objetivoDaoService.findById(informeDto.getObjetivoId())
                .orElseThrow(() -> new IllegalArgumentException("Objetivo no encontrado"));

        UsuarioRegistro usuario = usuarioRegistroDaoService.findById(objetivo.getUsuario().getIdUsuarioRegistro())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        int edad = calcularEdad(usuario.getFechaNacimiento());
        Sexo sexo = usuario.getSexo();
        Double peso = informeDto.getPeso();
        int altura = informeDto.getAltura();

        // Convertir el DTO en entidad (MapStruct convertirá fechaRegistro String -> Date usando el dateFormat)
        Informe informe = mapperFactory.getInformeMapper().toEntity(informeDto);

        // Sobrescribir los cálculos que no deben venir del cliente
        informe.setEdad(edad);
        informe.setSexo(sexo);
        informe.setImc(calculateIMC(peso, altura));
        informe.setTmb(calculateTMB(peso, altura, edad, sexo.name(), informeDto.getDiasEntreno()));

        // Guardar
        Informe saved = informeDaoService.save(informe);

        // Devolver DTO
        return mapperFactory.getInformeMapper().toDTO(saved);
    }

    private int calcularEdad(Date fechaNacimiento) {
        // Calcula la edad en años teniendo en cuenta si ya ha cumplido años este año
        LocalDate fechaNac = fechaNacimiento.toLocalDate();
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - fechaNac.getYear();
        if (hoy.getDayOfYear() < fechaNac.getDayOfYear()){
            edad = -1;
        }
        return edad;
    }


    private static Double calculateTMB(Double peso, int altura, int edad, String sexo, int diasEntreno) {
        double tmbAprox = (10 * peso) + (6.25 * altura) - (5 * edad) + (sexo.equals("MASCULINO") ? 5 : -161);

        double nivelActividad;
        if (diasEntreno == 0) nivelActividad = 1.2;
        else if (diasEntreno < 4) nivelActividad = 1.375;
        else if (diasEntreno < 6) nivelActividad = 1.55;
        else if (diasEntreno < 8) nivelActividad = 1.725;
        else nivelActividad = 1.9;

        return tmbAprox * nivelActividad;
    }

    private static Double calculateIMC(Double peso, int altura) {
        return peso / Math.pow(altura / 100.0, 2);
    }

    @Override
    public InformeDTO findById(Long id) {
        return informeDaoService.findById(id)
                .map(mapperFactory.getInformeMapper()::toDTO)
                .orElse(null);
    }

    @Override
    public void delete(InformeDTO informeDto) {
        Informe informe = mapperFactory.getInformeMapper().toEntity(informeDto);
        informeDaoService.delete(informe);
    }

    @Override
    public List<InformeDTO> findListByIdObjetivo(Long id) {
        return informeDaoService.findListByObjetivoId(id).stream()
                .map(mapperFactory.getInformeMapper()::toDTO)
                .toList();
    }
}
