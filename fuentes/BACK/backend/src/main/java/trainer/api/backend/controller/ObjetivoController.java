package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.entity.Objetivo;
import trainer.api.backend.service.IObjetivo;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ObjetivoController {

    private final IObjetivo objetivoService;

    @PostMapping("objetivo")
    public Objetivo create(@RequestBody Objetivo objetivo){
        return objetivoService.save(objetivo);
    }

    @PutMapping("objetivo")
    public Objetivo update(@RequestBody Objetivo objetivo){
        return objetivoService.save(objetivo);
    }

    @DeleteMapping("objetivo/{id}")
    public void deleteById(@PathVariable Long id){
        var objetivo = objetivoService.findById(id);
        if (!Objects.isNull(objetivo)){
            objetivoService.delete(objetivo);
        }
    }

    @GetMapping("objetivo/{id}")
    public Objetivo showById(@PathVariable Long id){
        return objetivoService.findById(id);
    }
}
