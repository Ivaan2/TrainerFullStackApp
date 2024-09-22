package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.dto.LoginRequestDto;
import trainer.api.backend.model.dto.UsuarioRegistroDTO;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.model.payload.MensajeResponse;
import trainer.api.backend.service.IUsuarioRegistro;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

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
            var nuevo_usuario = new UsuarioRegistro();
            usuarioDTOToEntity(usuarioRegistroDto, nuevo_usuario);
            usuarioRegistroService.save(nuevo_usuario);
            log.info("Entidad guardada en base de datos");
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(nuevo_usuario)
                    .build()
                    , HttpStatus.CREATED);
        }
        return new ResponseEntity<>(MensajeResponse.builder().mensaje("Se ha producido un error al guardar el usuario")
                .object(null).build(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/usuarioRegistro/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioRegistroDTO usuarioRegistroDto, @PathVariable Integer id) {
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
        log.info("Parseando DTO a Entidad");
        if (usuarioRegistroDto.getNombre() != null) {
            usuarioExistente.setNombre(usuarioRegistroDto.getNombre());
        }

        if (usuarioRegistroDto.getApellido1() != null) {
            usuarioExistente.setApellido1(usuarioRegistroDto.getApellido1());
        }

        if (usuarioRegistroDto.getApellido2() != null) {
            usuarioExistente.setApellido2(usuarioRegistroDto.getApellido2());
        }

        if (usuarioRegistroDto.getEmail() != null) {
            usuarioExistente.setEmail(usuarioRegistroDto.getEmail());
        }

        if (usuarioRegistroDto.getNombreUsuario() != null) {
            usuarioExistente.setNombreUsuario(usuarioRegistroDto.getNombreUsuario());
        }

        if (usuarioRegistroDto.getPais() != null) {
            usuarioExistente.setPais(usuarioRegistroDto.getPais());
        }

        if (usuarioRegistroDto.getSexo() != null) {
            usuarioExistente.setSexo(usuarioRegistroDto.getSexo());
        }

        if (usuarioRegistroDto.getRutaAvatar() != null) {
            usuarioExistente.setRutaAvatar(usuarioRegistroDto.getRutaAvatar());
        }

// Fecha de actualización siempre se debe setear a la fecha actual
        usuarioExistente.setFechaActualizacion(new Timestamp(new Date().getTime()));

// Codificar y setear la contraseña solo si no es nula
        if (usuarioRegistroDto.getPassword() != null) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuarioRegistroDto.getPassword()));
        }

        if (!Objects.isNull(usuarioRegistroDto.getEdad())) {
            usuarioExistente.setEdad(usuarioRegistroDto.getEdad());
        }

// Solo establecer las fechas si realmente son necesarias
        usuarioExistente.setFechaRegistro(new Timestamp(new Date().getTime()));
        usuarioExistente.setFechaActualizacion(null); // Si tienes lógica para esto
        usuarioExistente.setFechaBaja(null); // Si tienes lógica para esto también

    }


    @DeleteMapping("/usuarioRegistro/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        var usuarioRegistro = new UsuarioRegistro();
        try{
            usuarioRegistro = usuarioRegistroService.findById(id);
            usuarioRegistroService.delete(usuarioRegistro);
        }catch(DataAccessException e){
            log.info("No se ha podido encontrar el usuario con id: "+ id);
        }
        return new ResponseEntity<>(usuarioRegistro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/usuarioRegistro/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        log.info("** Lanzando GET Method **");
        var usuarioRegistro = usuarioRegistroService.findById(id);
        if (ObjectUtils.isEmpty(usuarioRegistro)) return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se ha encontrado un usuario")
                .object(usuarioRegistro).build(),
        HttpStatus.OK);
    }

    @PatchMapping("/usuarioRegistro/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Integer id, @RequestBody String password){
        log.info("*** Actualizando contraseña ***");
        if (ObjectUtils.isEmpty(password)){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("La contraseña no es válida")
                    .object(null).build(),
                HttpStatus.BAD_REQUEST);
        }
        var usuarioExistente = usuarioRegistroService.findById(id);
        if (ObjectUtils.isEmpty(usuarioExistente)){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No existe ese usuario")
                    .object(null).build(),
                HttpStatus.BAD_REQUEST);
        }
        usuarioExistente.setPassword(passwordEncoder.encode(password));
        usuarioRegistroService.save(usuarioExistente);
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Se ha actualizado la contraseña correctamente")
                .object(usuarioExistente).build(),
                HttpStatus.OK);
    }

    @GetMapping("usuarioRegistro/password/{id}")
    public ResponseEntity<?> getPasswordCodificada(@PathVariable Integer id){
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
        List<UsuarioRegistro> listaUsuarios = usuarioRegistroService.findAll();
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
    public ResponseEntity<?> cambiarAvatar(@PathVariable Integer id, @RequestBody Map<String, String> payload) {
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

}
