package trainer.api.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.dto.InformeDTO;
import trainer.api.backend.model.dto.ObjetivoDTO;
import trainer.api.backend.model.entity.Informe;
import trainer.api.backend.model.entity.Objetivo;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.model.payload.MensajeResponse;
import trainer.api.backend.service.IInforme;
import trainer.api.backend.service.IObjetivo;
import trainer.api.backend.service.IUsuarioRegistro;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ObjetivoController {

    private final IObjetivo objetivoService;
    private final IUsuarioRegistro usuarioRegistroService;
    private final IInforme informeService;

    @PostMapping("objetivo")
    public ResponseEntity<?> create(@RequestBody ObjetivoDTO objetivoDto) {
        Objetivo clienteSave;
        try {
            clienteSave = objetivoService.save(objetivoDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(ObjetivoDTO.builder()
                            .cumplido(clienteSave.getCumplido())
                            .descripcion(clienteSave.getDescripcion())
                            .fechaFin(TimestampToString(clienteSave.getFechaFin()))
                            .fechaRegistro(TimestampToString(clienteSave.getFechaRegistro()))
                            .id(clienteSave.getId())
                            .usuarioId(clienteSave.getUsuarioId())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("objetivo/{id}")
    public ResponseEntity<?> update(@RequestBody ObjetivoDTO objetivoDto, @PathVariable Long id) {
        Objetivo objetivoUpdate;
        try {
            if (objetivoService.existsById(id)) {
                objetivoDto.setId(id);
                objetivoUpdate = objetivoService.save(objetivoDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Guardado correctamente")
                        .object(ObjetivoDTO.builder()
                                .cumplido(objetivoUpdate.getCumplido())
                                .descripcion(objetivoUpdate.getDescripcion())
                                .fechaFin(TimestampToString(objetivoUpdate.getFechaFin()))
                                .fechaRegistro(TimestampToString(objetivoUpdate.getFechaRegistro()))
                                .id(objetivoUpdate.getId())
                                .usuarioId(objetivoUpdate.getUsuarioId())
                                .build())
                        .build()
                        , HttpStatus.OK);
            }
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("objetivo/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            Objetivo objetivoDelete = objetivoService.findById(id);
            if (ObjectUtils.isNotEmpty(objetivoDelete)) {
                objetivoService.delete(objetivoDelete);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Registro eliminado correctamente")
                        .object(objetivoDelete).build()
                        , HttpStatus.OK);
            }
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No existe ese registro en la base de datos")
                    .object(null).build(), HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.CONFLICT);
        }
    }

    @GetMapping("objetivo/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        var objetivo = objetivoService.findById(id);

        if (ObjectUtils.isNotEmpty(objetivo)) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Registro rescatado correctamente")
                            .object(ObjetivoDTO.builder()
                                    .cumplido(objetivo.getCumplido())
                                    .descripcion(objetivo.getDescripcion())
                                    .fechaFin(TimestampToString(objetivo.getFechaFin()))
                                    .fechaRegistro(TimestampToString(objetivo.getFechaRegistro()))
                                    .id(objetivo.getId())
                                    .usuarioId(objetivo.getUsuarioId())
                                    .build())
                            .build()
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("El registro que intenta buscar no existe")
                        .object(null)
                        .build()
                , HttpStatus.NOT_FOUND);
    }

    @GetMapping("objetivos/{id}")
    public ResponseEntity<?> obtenerListaByIdUsuario(@PathVariable Integer id) {
        log.info("*** Obteniendolista de objetivos para un usuario ***");
        if (ObjectUtils.isEmpty(id) || id == 0) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El id de usuario no es correcto")
                    .object(null).build(),
                    HttpStatus.BAD_REQUEST);
        }
        var usuario = usuarioRegistroService.findById(id);
        if (ObjectUtils.isEmpty(usuario)) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El id de usuario no se encuentra en el sistema")
                    .object(null).build(),
                    HttpStatus.NOT_FOUND);
        }
        List<Objetivo> listaObjetivos = objetivoService.findListByUserId(id);
        List<ObjetivoDTO> listaObjetivosDTO = listaObjetivos.stream().map(objetivo -> ObjetivoDTO.builder()
                .id(objetivo.getId())
                .descripcion(objetivo.getDescripcion())
                .fechaRegistro(TimestampToString(objetivo.getFechaRegistro()))
                .fechaFin(TimestampToString(objetivo.getFechaFin()))
                .cumplido(objetivo.getCumplido())
                .usuarioId(objetivo.getUsuarioId())
                .informes(listaInformesToDTO(objetivo.getId()))
                .build()).toList();

        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se devuelve la lista de objetivos relacionados al usuario")
                .object(listaObjetivosDTO).build(),
                HttpStatus.OK);
    }

    @GetMapping("objetivo/user/{idUsuario}")
    public ResponseEntity<?> obtenerUltimoObjetivo(@PathVariable Integer idUsuario) {
        log.info("*** Obteniendo el último objetivo del usuario ***");
        if (ObjectUtils.isEmpty(idUsuario) || idUsuario == 0) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El id de usuario no es correcto")
                    .object(null).build(),
                    HttpStatus.BAD_REQUEST);
        }
        var usuario = usuarioRegistroService.findById(idUsuario);
        if (ObjectUtils.isEmpty(usuario)) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El id de usuario no se encuentra en el sistema")
                    .object(null).build(),
                    HttpStatus.NOT_FOUND);
        }
        Objetivo ultimoObjetivo = objetivoService.findLastByUserId(idUsuario);
        if(ObjectUtils.isEmpty(ultimoObjetivo)) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No se encontraron objetivos relacionados al usuario")
                    .object(null).build(),
                    HttpStatus.NO_CONTENT);
        }

        List<InformeDTO> informesDto = listaInformesToDTO(ultimoObjetivo.getId());
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se devuelve el último objetivo relacionado al usuario")
                .object(ObjetivoDTO.builder()
                        .cumplido(ultimoObjetivo.getCumplido())
                        .descripcion(ultimoObjetivo.getDescripcion())
                        .fechaFin(TimestampToString(ultimoObjetivo.getFechaFin()))
                        .fechaRegistro(TimestampToString(ultimoObjetivo.getFechaRegistro()))
                        .id(ultimoObjetivo.getId())
                        .usuarioId(ultimoObjetivo.getUsuarioId())
                        .informes(informesDto)
                        .build()).build(),
                HttpStatus.OK);
    }

    private List<InformeDTO> listaInformesToDTO(Long objetivoId) {
        List<Informe> listaInformes = informeService.findListByIdObjetivo(objetivoId);

        return listaInformes.stream().map(informe -> InformeDTO.builder()
                .id(informe.getId())
                .objetivoId(objetivoId)
                .altura(informe.getAltura())
                .peso(informe.getPeso())
                .cadera(informe.getCadera())
                .gemelos(informe.getGemelos())
                .cuadriceps(informe.getCuadriceps())
                .abdomen(informe.getAbdomen())
                .pecho(informe.getPecho())
                .hombros(informe.getHombros())
                .antebrazo(informe.getAntebrazo())
                .biceps(informe.getBiceps())
                .gluteos(informe.getGluteos())
                .porcentajeGraso(informe.getPorcentajeGraso())
                .porcentajeMusculo(informe.getPorcentajeMusculo())
                .nivelActividad(informe.getNivelActividad())
                .diasEntreno(informe.getDiasEntreno())
                .seguimientoDieta(informe.getSeguimientoDieta())
                .imc(informe.getImc())
                .tmb(informe.getTmb())
                .fechaRegistro(TimestampToString(informe.getFechaRegistro()))
                .build()).toList();
    }

    private String TimestampToString(Timestamp fechaRegistro) {
        LocalDateTime localDateTime = fechaRegistro.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Formatear el LocalDateTime a String
        return localDateTime.format(formatter);
    }
}
