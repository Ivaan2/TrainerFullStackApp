package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.dto.UsuarioRegistroDTO;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.model.payload.MensajeResponse;
import trainer.api.backend.service.IUsuarioRegistro;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UsuarioRegistroController {

    private final IUsuarioRegistro usuarioRegistroService;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("usuarioRegistro")
    public ResponseEntity<?> create(@RequestBody UsuarioRegistroDTO usuarioRegistroDto){
        if(ObjectUtils.isNotEmpty(usuarioRegistroDto)){
            var nuevo_usuario = new UsuarioRegistro();
            usuarioDTOToEntity(usuarioRegistroDto, nuevo_usuario);
            usuarioRegistroService.save(nuevo_usuario);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(nuevo_usuario)
                    .build()
                    , HttpStatus.CREATED);
        }
        return new ResponseEntity<>(usuarioRegistroDto, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("usuarioRegistro/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioRegistroDTO usuarioRegistroDto, @PathVariable Long id) {
        UsuarioRegistro usuarioExistente = usuarioRegistroService.findById(id);

        if (usuarioExistente != null) {
            // Actualizar los campos del usuario existente con los valores del DTO
            usuarioDTOToEntity(usuarioRegistroDto, usuarioExistente);

            // Guardar el usuario actualizado en la base de datos
            UsuarioRegistro usuarioActualizado = usuarioRegistroService.save(usuarioExistente);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(usuarioActualizado)
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se ha encontrado un registro con ese id ")
                .object(null)
                .build(), HttpStatus.NOT_FOUND);
    }

    private static void usuarioDTOToEntity(UsuarioRegistroDTO usuarioRegistroDto, UsuarioRegistro usuarioExistente) {
        usuarioExistente.setNombre(usuarioRegistroDto.getNombre());
        usuarioExistente.setApellido1(usuarioRegistroDto.getApellido1());
        usuarioExistente.setApellido2(usuarioRegistroDto.getApellido2());
        usuarioExistente.setEmail(usuarioRegistroDto.getEmail());
        usuarioExistente.setTelefono(usuarioRegistroDto.getTelefono());
        usuarioExistente.setFechaActualizacion(new Timestamp(new Date().getTime()));
        usuarioExistente.setPassword(passwordEncoder.encode(usuarioRegistroDto.getPassword()));
    }


    @DeleteMapping("usuarioRegistro/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        var usuarioRegistro = new UsuarioRegistro();
        try{
            usuarioRegistro = usuarioRegistroService.findById(id);
            usuarioRegistroService.delete(usuarioRegistro);
        }catch(DataAccessException e){
            System.out.println("No se ha podido encontrar el usuario con id: "+ id);
        }
        return new ResponseEntity<>(usuarioRegistro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("usuarioRegistro/{id}")
    public UsuarioRegistro showById(@PathVariable Long id){
        return usuarioRegistroService.findById(id);
    }
}
