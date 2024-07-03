package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.dto.ComidaDTO;
import trainer.api.backend.model.entity.Comida;
import trainer.api.backend.model.payload.MensajeResponse;
import trainer.api.backend.service.IComida;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/")
public class ComidaController {

    private final IComida comidaService;

    @PostMapping("comida")
    public ResponseEntity<?> create(@RequestBody ComidaDTO comidaDto){
        if (ObjectUtils.isNotEmpty(comidaDto)){
            var comidaObj = toEntity(comidaDto);
            comidaService.save(comidaObj);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se ha creado el registro con éxito")
                    .object(comidaObj).build()
            , HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se ha podido guardar el registro")
                .object(null).build()
        , HttpStatus.BAD_REQUEST);
    }

    @PutMapping("comida/{id}")
    public ResponseEntity<?> update(@RequestBody ComidaDTO comidaDto, @PathVariable Long id) {
        var comidaObj = findComidaById(id); // Método que encuentra la comida por ID
        if (comidaObj != null) {
            // Copia las propiedades del DTO al objeto existente
            copyProperties(comidaDto, comidaObj);
            comidaService.save(comidaObj);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se actualiza el registro con éxito")
                    .object(comidaObj).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se ha encontrado registro con ese id")
                .object(null).build(), HttpStatus.BAD_REQUEST);
    }

    public static Comida toEntity(ComidaDTO dto) {
        if (dto == null) {
            return null;
        }

        Comida entity = new Comida();
        entity.setId(dto.getId());
        entity.setDietaDiaria(dto.getDietaDiaria());
        entity.setNombre(dto.getNombre());
        entity.setHora(dto.getHora());
        entity.setAporteCalorico(dto.getAporteCalorico());
        entity.setAporteProteico(dto.getAporteProteico());
        entity.setAporteGrasa(dto.getAporteGrasa());
        entity.setAporteCarbohidratos(dto.getAporteCarbohidratos());

        return entity;
    }

    public void copyProperties(ComidaDTO source, Comida target) {
        BeanUtils.copyProperties(source, target, "id"); // No copiar el ID
    }

    private Comida findComidaById(Long id) {
        return comidaService.findById(id);
    }

    @DeleteMapping("comida/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        var comida = findComidaById(id);
        if (!Objects.isNull(comida)){
            comidaService.delete(comida);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se ha eliminado el registro con éxito")
                    .object(comida).build()
            , HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se ha encontrado registro con ese id")
                .object(null).build()
        , HttpStatus.BAD_REQUEST);
    }

    @GetMapping("comida/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id){
        var comida = findComidaById(id);
        if (!Objects.isNull(comida)){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se ha encontrado el registro con éxito")
                    .object(comida).build()
                , HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se ha encontrado registro con ese id")
                .object(null).build()
            , HttpStatus.BAD_REQUEST);
    }

    @GetMapping("comidas/{id}")
    public ResponseEntity<?> showAllByDietaId(@PathVariable Long id){
        List<Comida> listaComidas = comidaService.findList(id);
        if (ObjectUtils.isNotEmpty(listaComidas)){
            return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se han encontrado registros")
                .object(listaComidas).build()
            , HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se han encontrado registros con ese id")
                .object(null).build()
        , HttpStatus.BAD_REQUEST);
    }
}
