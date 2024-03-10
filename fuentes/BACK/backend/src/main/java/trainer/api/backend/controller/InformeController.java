package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.entity.Informe;
import trainer.api.backend.service.IInforme;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class InformeController {

    private final IInforme informeService;

    @PostMapping("informe")
    public Informe create(@RequestBody Informe informe){
        return informeService.save(informe);
    }

    @PutMapping("informe")
    public Informe update(@RequestBody Informe informe){
        return informeService.save(informe);
    }

    @DeleteMapping("informe/{id}")
    public void deleteById(@PathVariable Long id){
        var informe = informeService.findById(id);
        if (!Objects.isNull(informe)){
            informeService.delete(informe);
        }
    }

    @GetMapping("informe/{id}")
    public Informe showById(@PathVariable Long id){
        return informeService.findById(id);
    }
}
