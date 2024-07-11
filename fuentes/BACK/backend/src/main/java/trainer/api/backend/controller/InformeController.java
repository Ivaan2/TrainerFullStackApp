package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.dto.InformeDTO;
import trainer.api.backend.model.payload.MensajeResponse;
import trainer.api.backend.service.IInforme;
import trainer.api.backend.service.IObjetivo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class InformeController {

    private final IInforme informeService;
    private final IObjetivo objetivoService;

    @PostMapping("informe")
    public ResponseEntity<?> create(@RequestBody InformeDTO informeDto){
        if(ObjectUtils.isNotEmpty(informeDto)){
            var informe = informeService.save(informeDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se ha creado el informe con éxito")
                    .object(InformeDTO.builder()
                            .id(informe.getId())
                            .objetivoId(informe.getObjetivoId())
                            .edad(informe.getEdad())
                            .sexo(informe.getSexo())
                            .altura(informe.getAltura())
                            .peso(informe.getPeso())
                            .porcentajeGraso(informe.getPorcentajeGraso())
                            .porcentajeMusculo(informe.getPorcentajeMusculo())
                            .nivelActividad(informe.getNivelActividad())
                            .imc(informe.getImc())
                            .tmb(informe.getTmb())
                            .fechaRegistro(new Timestamp(new Date().getTime())).build())
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("Formato inválido.")
                    .object(null)
                    .build()
                , HttpStatus.BAD_REQUEST);
    }

    @PutMapping("informe/{id}")
    public ResponseEntity<?> update(@RequestBody InformeDTO informeDto, @PathVariable Long id){
        if(ObjectUtils.isNotEmpty(informeDto)){
            if(ObjectUtils.isNotEmpty(informeService.findById(id))){
                informeDto.setId(id);
                var informe = informeService.save(informeDto);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Se actuliza el registro con éxito")
                                .object(informe).build()
                , HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("No existe un informe por ese id")
                        .object(null)
                , HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("informe/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        var informe = informeService.findById(id);
        if (!Objects.isNull(informe)){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se borra el objeto con éxito")
                    .object(informe)
            , HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se ha encontrado un informe con ese id")
                .object(null)
        , HttpStatus.BAD_REQUEST);
    }

    @GetMapping("informe/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id){
        var informeReturn = informeService.findById(id);
        if (ObjectUtils.isNotEmpty(informeReturn)){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se recoge el informe con éxito")
                    .object(informeReturn).build()
            , HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No existe un informe con ese id")
                .object(null)
        , HttpStatus.BAD_REQUEST);
    }

    @GetMapping("informes/{id}")
    public ResponseEntity<?> obtenerListaByIdObjetivo(@PathVariable Long id){
        if (ObjectUtils.isEmpty(id) || id==0){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El id es incorrecto")
                    .object(null).build(),
                HttpStatus.BAD_REQUEST);
        }
        var objetivoExistente = objetivoService.findById(id);
        if (ObjectUtils.isEmpty(objetivoExistente)){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El objetivo que busca no existe")
                    .object(null).build(),
                    HttpStatus.BAD_REQUEST);
        }
        var listaInformes = informeService.findListByIdObjetivo(id);
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se han encontrado una lista de informes")
                .object(listaInformes).build(),
            HttpStatus.OK);
    }
















}