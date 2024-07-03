package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.dto.DietaDiariaDTO;
import trainer.api.backend.model.payload.MensajeResponse;
import trainer.api.backend.service.IDietaDiaria;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class DietaDiariaController {

    private final IDietaDiaria dietaDiariaService;

    @PostMapping("dietaDiaria")
    public ResponseEntity<?> create(@RequestBody DietaDiariaDTO dietaDiariaDto){
        if(ObjectUtils.isNotEmpty(dietaDiariaDto)){
            var dieta = dietaDiariaService.save(dietaDiariaDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se ha añadido con éxito")
                    .object(dieta).build()
            , HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se ha podido guardar el registro")
                .object(null).build()
        , HttpStatus.BAD_REQUEST);
    }

    @PutMapping("dietaDiaria/{id}")
    public ResponseEntity<?> update(@RequestBody DietaDiariaDTO dietaDiariaDto, @PathVariable Long id){
        var dieta = dietaDiariaService.findById(id);
        if (ObjectUtils.isNotEmpty(dieta)){
            dietaDiariaDto.setId(id);
            dieta = dietaDiariaService.save(dietaDiariaDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se actualiza el registro con éxito")
                    .object(dieta).build()
            , HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se ha encontrado ningún registro con ese id")
                .object(null).build()
        , HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("dietaDiaria/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        var dieta = dietaDiariaService.findById(id);
        if (ObjectUtils.isNotEmpty(dieta)){
            dietaDiariaService.delete(dieta);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se elimina el registro con éxito")
                    .object(dieta).build()
                ,HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se ha podido eliminar el registro")
                .object(null).build()
            ,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("dietaDiaria/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id){
        var dieta = dietaDiariaService.findById(id);
        if (ObjectUtils.isNotEmpty(dieta)){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se ha encontrado un registro con éxito")
                    .object(dieta).build()
                ,HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se ha encontrado el registro")
                .object(null).build()
            ,HttpStatus.BAD_REQUEST);
    }
}
