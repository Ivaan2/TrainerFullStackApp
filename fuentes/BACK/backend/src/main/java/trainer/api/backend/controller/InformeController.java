package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.dto.InformeDTO;
import trainer.api.backend.model.dto.ObjetivoDTO;
import trainer.api.backend.model.dto.UsuarioRegistroDTO;
import trainer.api.backend.model.entity.enums.Sexo;
import trainer.api.backend.model.payload.MensajeResponse;
import trainer.api.backend.service.IInforme;
import trainer.api.backend.service.IObjetivo;
import trainer.api.backend.service.IUsuarioRegistro;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/informe")
public class InformeController {

    private final IInforme informeService;
    private final IObjetivo objetivoService;
    private final IUsuarioRegistro usuarioRegistroService;

    @PostMapping
    public ResponseEntity<MensajeResponse> create(@RequestBody InformeDTO informeDto) {
        if (ObjectUtils.isEmpty(informeDto)) {
            return ResponseEntity.badRequest().body(
                    MensajeResponse.builder()
                            .mensaje("Formato inválido.")
                            .object(null)
                            .build()
            );
        }

        // Recuperar Objetivo asociado
        ObjetivoDTO objetivo = objetivoService.findById(informeDto.getObjetivoId());
        if (objetivo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    MensajeResponse.builder()
                            .mensaje("El objetivo asociado no existe.")
                            .object(null)
                            .build()
            );
        }

        // Recuperar Usuario asociado al objetivo
        UsuarioRegistroDTO usuario = usuarioRegistroService.findById(objetivo.getUsuario().getIdUsuarioRegistro());
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    MensajeResponse.builder()
                            .mensaje("El usuario asociado al objetivo no existe.")
                            .object(null)
                            .build()
            );
        }

        // Calcular edad (puedes moverlo a un util si lo usarás en más sitios)
        int edad = usuario.getFechaNacimiento().getYear() + 1900 >= LocalDate.now().getYear() ?
                0 : LocalDate.now().getYear() - (usuario.getFechaNacimiento().getYear() + 1900);
        Sexo sexo = usuario.getSexo();

        // Guardar informe
        InformeDTO savedInforme = informeService.save(informeDto);

        // Enriquecer la respuesta si deseas incluir edad y sexo
        savedInforme.setEdad(edad);
        savedInforme.setSexo(sexo);

        return ResponseEntity.ok(
                MensajeResponse.builder()
                        .mensaje("Se ha creado el informe con éxito")
                        .object(savedInforme)
                        .build()
        );
    }

    @PutMapping("informe/{id}")
    public ResponseEntity<?> update(@RequestBody InformeDTO informeDto, @PathVariable Long id) {
        if (ObjectUtils.isNotEmpty(informeDto)) {
            if (ObjectUtils.isNotEmpty(informeService.findById(id))) {
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
                        .object(null).build()
                , HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "informe/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        var informe = informeService.findById(id);
        if (!Objects.isNull(informe)) {
            informeService.delete(informe);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se borra el objeto con éxito")
                    .object(informe).build()
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se ha encontrado un informe con ese id")
                .object(null).build()
                , HttpStatus.BAD_REQUEST);
    }

    @GetMapping("informe/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        var informeReturn = informeService.findById(id);
        if (ObjectUtils.isNotEmpty(informeReturn)) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se recoge el informe con éxito")
                    .object(informeReturn).build()
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No existe un informe con ese id")
                .object(null).build()
                , HttpStatus.BAD_REQUEST);
    }

    @GetMapping("informes/{id}")
    public ResponseEntity<?> obtenerListaByIdObjetivo(@PathVariable Long id) {
        if (ObjectUtils.isEmpty(id) || id == 0) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El id es incorrecto")
                    .object(null).build(),
                    HttpStatus.BAD_REQUEST);
        }
        var objetivoExistente = objetivoService.findById(id);
        if (ObjectUtils.isEmpty(objetivoExistente)) {
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

    @GetMapping("informes/lastObjetivo/{userId}")
    public ResponseEntity<?> obtenerListaInformeUltimoObjetivoByUserId(@PathVariable Long userId) {
        if (ObjectUtils.isEmpty(userId) || userId == 0) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El id es incorrecto")
                    .object(null).build(),
                    HttpStatus.BAD_REQUEST);
        }
        var ultimoObjetivo = objetivoService.findLastByUserId(userId);
        if (ObjectUtils.isEmpty(ultimoObjetivo)) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No se han encontrado objetivos para este usuario")
                    .object(null).build(),
                    HttpStatus.BAD_REQUEST);
        }
        var listaInformes = informeService.findListByIdObjetivo(ultimoObjetivo.getId());
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se han encontrado una lista de informes")
                .object(listaInformes).build(),
                HttpStatus.OK);
    }

    @PostMapping("/lastObjetivo/{userId}")
    public ResponseEntity<?> createInformeLastObjetivoByUserId(@RequestBody InformeDTO informeDto, @PathVariable Long userId) {
        if (ObjectUtils.isNotEmpty(informeDto)) {
            var ultimoObjetivo = usuarioRegistroService.findById(userId).getLastObjetivo();
            if (ObjectUtils.isEmpty(ultimoObjetivo)) {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("No se han encontrado objetivos para este usuario")
                        .object(null).build(),
                        HttpStatus.BAD_REQUEST);
            }
            // Ajustamos el id para crear el informe en el objetivo mas reciente
            informeDto.setObjetivoId(ultimoObjetivo.getId());
            var informe = informeService.save(informeDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se ha creado el informe con éxito")
                    .object(informe).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Formato inválido.")
                        .object(null)
                        .build()
                , HttpStatus.BAD_REQUEST);
    }

}