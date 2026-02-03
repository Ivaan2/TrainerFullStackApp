package trainer.api.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.config.mapper.MapperFactory;
import trainer.api.backend.model.dto.InformeDTO;
import trainer.api.backend.model.dto.ObjetivoDTO;
import trainer.api.backend.model.entity.Informe;
import trainer.api.backend.model.entity.Objetivo;
import trainer.api.backend.model.payload.MensajeResponse;
import trainer.api.backend.service.IInforme;
import trainer.api.backend.service.IObjetivo;
import trainer.api.backend.service.IUsuarioRegistro;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/objetivo")
public class ObjetivoController {

    private final IObjetivo objetivoService;
    private final IUsuarioRegistro usuarioRegistroService;
    private final IInforme informeService;
    private MapperFactory mapperFactory;

    @PostMapping
    public ResponseEntity<MensajeResponse> create(@RequestBody ObjetivoDTO objetivoDto) {
        try {
            ObjetivoDTO saved = objetivoService.save(objetivoDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(MensajeResponse.builder()
                            .mensaje("Guardado correctamente")
                            .object(saved)
                            .build());
        } catch (DataAccessException ex) {
            log.error("Error al guardar objetivo", ex);
            return ResponseEntity
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body(MensajeResponse.builder()
                            .mensaje("Error al guardar objetivo: " + ex.getMessage())
                            .object(null)
                            .build());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeResponse> update(@RequestBody ObjetivoDTO objetivoDto, @PathVariable Long id) {
        try {
            if (!objetivoService.existsById(id)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(MensajeResponse.builder()
                                .mensaje("El registro que intenta actualizar no existe")
                                .object(null)
                                .build());
            }

            objetivoDto.setId(id);
            ObjetivoDTO updated = objetivoService.save(objetivoDto);

            return ResponseEntity
                    .ok(MensajeResponse.builder()
                            .mensaje("Actualizado correctamente")
                            .object(updated)
                            .build());

        } catch (DataAccessException ex) {
            log.error("Error al actualizar objetivo con id {}", id, ex);
            return ResponseEntity
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body(MensajeResponse.builder()
                            .mensaje("Error al actualizar objetivo: " + ex.getMessage())
                            .object(null)
                            .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponse> deleteById(@PathVariable Long id) {
        try {
            ObjetivoDTO objetivo = objetivoService.findById(id);
            if (objetivo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(MensajeResponse.builder()
                                .mensaje("No existe ese registro en la base de datos")
                                .object(null)
                                .build());
            }
            objetivoService.delete(objetivo);
            return ResponseEntity.ok(
                MensajeResponse.builder()
                    .mensaje("Registro eliminado correctamente")
                    .object(objetivo)
                    .build()
            );
        } catch (DataAccessException exDt) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(MensajeResponse.builder()
                            .mensaje(exDt.getMostSpecificCause().getMessage())
                            .object(null)
                            .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeResponse> showById(@PathVariable Long id) {
        ObjetivoDTO objetivo = objetivoService.findById(id);
        if (objetivo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar no existe")
                            .object(null)
                            .build());
        }

        return ResponseEntity.ok(
                MensajeResponse.builder()
                        .mensaje("Registro rescatado correctamente")
                        .object(objetivo)
                        .build()
        );
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<MensajeResponse> obtenerListaByIdUsuario(@PathVariable Long idUsuario) {
        log.info("*** Obteniendo lista de objetivos para un usuario ***");

        if (idUsuario == null || idUsuario <= 0) {
            return ResponseEntity.badRequest()
                    .body(MensajeResponse.builder()
                            .mensaje("El id de usuario no es correcto")
                            .object(null)
                            .build());
        }

        var usuario = usuarioRegistroService.findById(idUsuario);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(MensajeResponse.builder()
                            .mensaje("El id de usuario no se encuentra en el sistema")
                            .object(null)
                            .build());
        }

        List<ObjetivoDTO> listaObjetivosDTO = objetivoService.findListByUserId(idUsuario);

        return ResponseEntity.ok(
                MensajeResponse.builder()
                        .mensaje("Se devuelve la lista de objetivos relacionados al usuario")
                        .object(listaObjetivosDTO)
                        .build()
        );
    }

    @GetMapping("/user/{idUsuario}")
    public ResponseEntity<MensajeResponse> obtenerUltimoObjetivo(@PathVariable Long idUsuario) {
        log.info("*** Obteniendo el último objetivo del usuario ***");

        if (idUsuario == null || idUsuario <= 0) {
            return ResponseEntity.badRequest()
                    .body(MensajeResponse.builder()
                            .mensaje("El id de usuario no es correcto")
                            .object(null)
                            .build());
        }

        var usuario = usuarioRegistroService.findById(idUsuario);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(MensajeResponse.builder()
                            .mensaje("El id de usuario no se encuentra en el sistema")
                            .object(null)
                            .build());
        }

        ObjetivoDTO ultimoObjetivoDto = usuario.getObjetivos().get(usuario.getObjetivos().size() - 1);
        if (ultimoObjetivoDto == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(MensajeResponse.builder()
                            .mensaje("No se encontraron objetivos relacionados al usuario")
                            .object(null)
                            .build());
        }

        return ResponseEntity.ok(
                MensajeResponse.builder()
                        .mensaje("Se devuelve el último objetivo relacionado al usuario")
                        .object(ultimoObjetivoDto)
                        .build()
        );
    }
}
