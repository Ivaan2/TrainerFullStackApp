package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.config.mapper.MapperFactory;
import trainer.api.backend.model.dto.ComidaDTO;
import trainer.api.backend.model.entity.Comida;
import trainer.api.backend.model.payload.MensajeResponse;
import trainer.api.backend.service.IComida;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/")
public class ComidaController {

    private final IComida comidaService;
    private final MapperFactory mapperFactory;

    @PostMapping("comida")
    public ResponseEntity<MensajeResponse> create(@RequestBody ComidaDTO comidaDto) {
        if (ObjectUtils.isEmpty(comidaDto)) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No se ha podido guardar el registro")
                    .object(null)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        Comida comidaEntity = mapperFactory.getComidaMapper().toEntity(comidaDto);
        ComidaDTO savedDto = comidaService.save(mapperFactory.getComidaMapper().toDTO(comidaEntity));

        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se ha creado el registro con éxito")
                .object(savedDto)
                .build(), HttpStatus.CREATED);
    }

    @PutMapping("comida/{id}")
    public ResponseEntity<MensajeResponse> update(@RequestBody ComidaDTO comidaDto, @PathVariable Long id) {
        ComidaDTO existing = comidaService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No se ha encontrado registro con ese id")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }

        // Actualizamos los campos excepto el ID
        comidaDto.setId(id);
        ComidaDTO updated = comidaService.save(comidaDto);

        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se actualiza el registro con éxito")
                .object(updated)
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("comida/{id}")
    public ResponseEntity<MensajeResponse> deleteById(@PathVariable Long id) {
        ComidaDTO existing = comidaService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No se ha encontrado registro con ese id")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }

        comidaService.delete(existing);
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se ha eliminado el registro con éxito")
                .object(existing)
                .build(), HttpStatus.OK);
    }

    @GetMapping("comida/{id}")
    public ResponseEntity<MensajeResponse> showById(@PathVariable Long id) {
        ComidaDTO existing = comidaService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No se ha encontrado registro con ese id")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se ha encontrado el registro con éxito")
                .object(existing)
                .build(), HttpStatus.OK);
    }

    @GetMapping("comidas/{idDieta}")
    public ResponseEntity<MensajeResponse> showAllByDietaId(@PathVariable Long idDieta) {
        List<ComidaDTO> listaComidas = comidaService.findList(idDieta);

        if (ObjectUtils.isEmpty(listaComidas)) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No se han encontrado registros con ese id")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se han encontrado registros")
                .object(listaComidas)
                .build(), HttpStatus.OK);
    }
}
