package trainer.api.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import trainer.api.backend.model.entity.DietaDiaria;
import trainer.api.backend.service.IDietaDiaria;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class DietaDiariaController {

    private final IDietaDiaria dietaDiariaService;

    @PostMapping("dietaDiaria")
    public DietaDiaria create(@RequestBody DietaDiaria dietaDiaria){
        return dietaDiariaService.save(dietaDiaria);
    }

    @PutMapping("dietaDiaria")
    public DietaDiaria update(@RequestBody DietaDiaria dietaDiaria){
        return dietaDiariaService.save(dietaDiaria);
    }

    @DeleteMapping("dietaDiaria/{id}")
    public void deleteById(@PathVariable Long id){
        var dieta = dietaDiariaService.findById(id);
        if (!Objects.isNull(dieta)){
            dietaDiariaService.delete(dieta);
        }
    }

    @GetMapping("dietaDiaria/{id}")
    public DietaDiaria showById(@PathVariable Long id){
        return dietaDiariaService.findById(id);
    }
}
