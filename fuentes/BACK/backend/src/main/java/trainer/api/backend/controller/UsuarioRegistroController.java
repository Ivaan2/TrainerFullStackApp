package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.dto.UsuarioRegistroDTO;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.service.IUsuarioRegistro;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UsuarioRegistroController {

    private final IUsuarioRegistro usuarioRegistroService;

    @PostMapping("usuarioRegistro")
    public UsuarioRegistro create(@RequestBody UsuarioRegistroDTO usuarioRegistroDto){
        var usuarioRegistro = new UsuarioRegistro();
        return usuarioRegistroService.save(usuarioRegistroDto);
    }

    @PutMapping("usuarioRegistro")
    // TODO: Aplicar ResponseEntity a todas las peticiones
    public UsuarioRegistro update(@RequestBody UsuarioRegistroDTO usuarioRegistro){
        return usuarioRegistroService.save(usuarioRegistro);
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
