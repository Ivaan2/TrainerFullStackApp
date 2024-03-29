package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.entity.UsuarioRegistro;
import trainer.api.backend.service.IUsuarioRegistro;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UsuarioRegistroController {

    private final IUsuarioRegistro usuarioRegistroService;

    @PostMapping("usuarioRegistro")
    public UsuarioRegistro create(@RequestBody UsuarioRegistro usuarioRegistro){
        return usuarioRegistroService.save(usuarioRegistro);
    }

    @PutMapping("usuarioRegistro")
    public UsuarioRegistro update(@RequestBody UsuarioRegistro usuarioRegistro){
        return usuarioRegistroService.save(usuarioRegistro);
    }

    @DeleteMapping("usuarioRegistro/{id}")
    public void deleteById(@PathVariable Long id){
        var usuarioRegistro = usuarioRegistroService.findById(id);
        if (!Objects.isNull(usuarioRegistro)){
            usuarioRegistroService.delete(usuarioRegistro);
        }
    }

    @GetMapping("usuarioRegistro/{id}")
    public UsuarioRegistro showById(@PathVariable Long id){
        return usuarioRegistroService.findById(id);
    }
}
