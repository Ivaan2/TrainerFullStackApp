package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.dto.ObjetivoDTO;
import trainer.api.backend.model.entity.Objetivo;
import trainer.api.backend.model.payload.MensajeResponse;
import trainer.api.backend.service.IObjetivo;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ObjetivoController {

    private final IObjetivo objetivoService;

    @PostMapping("objetivo")
    public ResponseEntity<?> create(@RequestBody ObjetivoDTO objetivoDto){
        Objetivo clienteSave = null;
        try {
            clienteSave = objetivoService.save(objetivoDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(ObjetivoDTO.builder()
                            .cumplido(objetivoDto.getCumplido())
                            .descripcion(objetivoDto.getDescripcion())
                            .fechaFin(objetivoDto.getFechaFin())
                            .fechaRegistro(objetivoDto.getFechaRegistro())
                            .id(objetivoDto.getId())
                            .usuarioId(objetivoDto.getUsuarioId())
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

    @PutMapping("objetivo")
    public ResponseEntity<?> update(@RequestBody ObjetivoDTO objetivoDto, @PathVariable Long id){
        Objetivo objetivoUpdate = null;
        try {
            if (objetivoService.existsById(id)) {
                objetivoDto.setId(id);
                objetivoUpdate = objetivoService.save(objetivoDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Guardado correctamente")
                        .object(ObjetivoDTO.builder()
                                .cumplido(objetivoUpdate.getCumplido())
                                .descripcion(objetivoUpdate.getDescripcion())
                                .fechaFin(objetivoUpdate.getFechaFin())
                                .fechaRegistro(objetivoUpdate.getFechaRegistro())
                                .id(objetivoUpdate.getId())
                                .usuarioId(objetivoUpdate.getUsuarioId())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }
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
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            Objetivo objetivoDelete = objetivoService.findById(id);
            objetivoService.delete(objetivoDelete);
            return new ResponseEntity<>(objetivoDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("objetivo/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id){
        Objetivo objetivo = objetivoService.findById(id);

        if (objetivo == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar, no existe!!")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(ObjetivoDTO.builder()
                                .cumplido(objetivo.getCumplido())
                                .descripcion(objetivo.getDescripcion())
                                .fechaFin(objetivo.getFechaFin())
                                .fechaRegistro(objetivo.getFechaRegistro())
                                .id(objetivo.getId())
                                .usuarioId(objetivo.getUsuarioId())
                                .build())
                        .build()
                , HttpStatus.OK);
    }
}
