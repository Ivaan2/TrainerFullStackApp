package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.dto.LoginRequestDto;
import trainer.api.backend.model.dto.UpdatePasswordDto;
import trainer.api.backend.model.dto.UsuarioRegistroDTO;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.model.payload.MensajeResponse;
import trainer.api.backend.service.IUsuarioRegistro;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class UsuarioRegistroController {

    private final IUsuarioRegistro usuarioRegistroService;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/usuarioRegistro")
    public ResponseEntity<?> create(@RequestBody UsuarioRegistroDTO usuarioRegistroDto){
        log.info("** Lanzando POST Method **");

        if(ObjectUtils.isNotEmpty(usuarioRegistroDto)){
            UsuarioRegistroDTO usuarioRegistroDTO = usuarioRegistroService.save(usuarioRegistroDto);

            log.info("Entidad guardada en base de datos");

            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(usuarioRegistroDTO)
                    .build()
                    , HttpStatus.CREATED);
        }

        return new ResponseEntity<>(MensajeResponse.builder().mensaje("Se ha producido un error al guardar el usuario")
                .object(null).build(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/usuarioRegistro/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioRegistroDTO usuarioRegistroDto, @PathVariable Long id) {
        UsuarioRegistroDTO usuarioExistenteDto = usuarioRegistroService.findById(id);

        if (usuarioExistenteDto != null) {

            // Guardar el usuario actualizado en la base de datos
            UsuarioRegistroDTO usuarioActualizadoDto = usuarioRegistroService.save(usuarioRegistroDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(usuarioActualizadoDto)
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("No se ha encontrado un registro con ese id ")
                .object(null)
                .build(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/usuarioRegistro/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        UsuarioRegistroDTO usuarioRegistroDTO = UsuarioRegistroDTO.builder().build();
        try{
            usuarioRegistroDTO = usuarioRegistroService.findById(id);
            usuarioRegistroService.delete(usuarioRegistroDTO);
        }catch(DataAccessException e){
            log.info("No se ha podido encontrar el usuario con id: "+ id);
            return new ResponseEntity<>(usuarioRegistroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se ha eliminado el usuario con éxito")
                .object(usuarioRegistroDTO).build(),
            HttpStatus.OK);
    }

    @GetMapping("/usuarioRegistro/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id){
        log.info("** Lanzando GET Method **");
        var usuarioRegistro = usuarioRegistroService.findById(id);
        if (ObjectUtils.isEmpty(usuarioRegistro)) return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se ha encontrado un usuario")
                .object(usuarioRegistro).build(),
        HttpStatus.OK);
    }

    @PatchMapping("/usuarioRegistro/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordDto updatePasswordDto) {
        String newPassword = updatePasswordDto.getNewPassword();
        String oldPassword = updatePasswordDto.getOldPassword();

        log.info("*** Actualizando contraseña ***");

        // Validar que la nueva contraseña no esté vacía
        if (ObjectUtils.isEmpty(newPassword)) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("La nueva contraseña no es válida")
                    .object(null).build(),
                    HttpStatus.BAD_REQUEST);
        }

        // Buscar el usuario por ID
        var usuarioExistente = usuarioRegistroService.findById(id);
        if (ObjectUtils.isEmpty(usuarioExistente)) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No existe ese usuario")
                    .object(null).build(),
                    HttpStatus.BAD_REQUEST);
        }

        // Obtener la contraseña antigua encriptada del usuario
        String passwordAntiguoExistenteCodificada = usuarioExistente.getPassword();

        // Comparar la contraseña antigua proporcionada con la almacenada
        if (!passwordEncoder.matches(oldPassword, passwordAntiguoExistenteCodificada)) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("La contraseña antigua no es correcta")
                    .object(null).build(),
                    HttpStatus.BAD_REQUEST);
        }

        // Codificar y actualizar la nueva contraseña
        usuarioExistente.setPassword(passwordEncoder.encode(newPassword));
        usuarioRegistroService.save(usuarioExistente);

        // Respuesta de éxito
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se ha actualizado la contraseña correctamente")
                .object(usuarioExistente).build(),
                HttpStatus.OK);
    }


    @GetMapping("usuarioRegistro/password/{id}")
    public ResponseEntity<?> getPasswordCodificada(@PathVariable Long id){
        if (ObjectUtils.isEmpty(id) || id==0){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El id proporcionado es incorrecto")
                    .object(null).build(),
                    HttpStatus.BAD_REQUEST);
        }
        var usuarioExistente = usuarioRegistroService.findById(id);
        if (ObjectUtils.isEmpty(usuarioExistente)){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No se ha encontrado el usuario")
                    .object(null).build(),
                HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("La contrseña ha sido encontrada correctamente")
                .object(usuarioExistente.getPassword()).build(),
            HttpStatus.OK);
    }

    @GetMapping("usuarioRegistro/getAll")
    public ResponseEntity<?> getAll(){
        List<UsuarioRegistroDTO> listaUsuarios = usuarioRegistroService.findAll();
        if (ObjectUtils.isEmpty(listaUsuarios)){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No se han encontrado usuarios")
                    .object(null).build(),
                HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder().mensaje("Se han encontrado registros")
                .object(listaUsuarios).build(), HttpStatus.OK);
    }

    @PostMapping("usuarioRegistro/login")
    public ResponseEntity<?> loginByUsernameAndPassword(@RequestBody LoginRequestDto loginRequestDto){
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        var usuario = usuarioRegistroService.findByEmail(email);
        if (ObjectUtils.isEmpty(usuario)){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No se ha encontrado el usuario")
                    .object(null).build(),
                HttpStatus.NOT_FOUND);
        }
        if (passwordEncoder.matches(password, usuario.getPassword())){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Usuario logueado correctamente")
                    .object(usuario).build(),
                HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("La contraseña no es correcta")
                .object(null).build(),
            HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("usuarioRegistro/cambiarAvatar/{id}")
    public ResponseEntity<?> cambiarAvatar(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        var usuario = usuarioRegistroService.findById(id);

        if (ObjectUtils.isEmpty(usuario)) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No se ha encontrado el usuario")
                    .object(null).build(),
                    HttpStatus.NOT_FOUND);
        }
        // Extraer el valor de 'rutaAvatar' del payload
        String rutaAvatar = payload.get("rutaAvatar");

        // Setear el avatar en el usuario
        usuario.setRutaAvatar(rutaAvatar);

        // Guardar el usuario actualizado
        usuario = usuarioRegistroService.save(usuario);

        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se ha actualizado el avatar correctamente")
                .object(usuario).build(),
                HttpStatus.OK);
    }

    @PatchMapping("usuarioRegistro/cambiarEmail/{id}")
    public ResponseEntity<?> cambiarEmail(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        var usuario = usuarioRegistroService.findById(id);

        if (ObjectUtils.isEmpty(usuario)) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No se ha encontrado el usuario")
                    .object(null).build(),
                    HttpStatus.NOT_FOUND);
        }
        // Extraer el valor de 'email' del payload
        String email = payload.get("email");
        var usuarioIncognita = usuarioRegistroService.findByEmail(email);
        if (ObjectUtils.isNotEmpty(usuarioIncognita)){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El email ya está en uso")
                    .object(null).build(),
                HttpStatus.IM_USED);
        }
        // Setear el email en el usuario
        usuario.setEmail(email);

        // Guardar el usuario actualizado
        usuario = usuarioRegistroService.save(usuario);

        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se ha actualizado el email correctamente")
                .object(usuario).build(),
                HttpStatus.OK);
    }

}
