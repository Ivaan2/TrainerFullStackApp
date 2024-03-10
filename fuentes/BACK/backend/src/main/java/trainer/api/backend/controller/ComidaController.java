package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.entity.Comida;
import trainer.api.backend.service.IComida;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/")
public class ComidaController {

    private final IComida comidaService;

    @PostMapping("comida")
    public Comida create(@RequestBody Comida comida){
        return comidaService.save(comida);
    }

    @PutMapping("comida")
    public Comida update(@RequestBody Comida comida){
        return comidaService.save(comida);
    }

    @DeleteMapping("comida/{id}")
    public void deleteById(@PathVariable Long id){
        var comida = comidaService.findById(id);
        if (!Objects.isNull(comida)){
            comidaService.delete(comida);
        }
    }

    @GetMapping("comida/{id}")
    public Comida showById(@PathVariable Long id){
        return comidaService.findById(id);
    }
}
