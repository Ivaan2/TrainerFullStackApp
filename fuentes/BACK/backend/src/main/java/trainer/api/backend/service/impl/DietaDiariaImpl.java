package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trainer.api.backend.model.dao.IDietaDiariaDao;
import trainer.api.backend.model.dto.DietaDiariaDTO;
import trainer.api.backend.model.entity.DietaDiaria;
import trainer.api.backend.service.IDietaDiaria;

@Service
@AllArgsConstructor
public class DietaDiariaImpl implements IDietaDiaria {

    public IDietaDiariaDao dietaDiariaDao;

    @Override
    public DietaDiaria save(DietaDiariaDTO dietaDto) {
        var dietaObj = DietaDiaria.builder()
            .dia(dietaDto.getDia())
            .comidasDiarias(dietaDto.getComidasDiarias())
            .caloriasTotales(dietaDto.getCaloriasTotales())
            .informeId(dietaDto.getInformeId())
            .requerimientoAgua(dietaDto.getRequerimientoAgua())
            .requerimientoCarbohidratos(dietaDto.getRequerimientoCarbohidratos())
            .requerimientoGrasa(dietaDto.getRequerimientoGrasa())
            .requerimientoProteico(dietaDto.getRequerimientoProteico()).build();
        return dietaDiariaDao.save(dietaObj);
    }

    @Override
    public DietaDiaria findById(Long id) {
        return dietaDiariaDao.findById(id).orElse(null);
    }

    @Override
    public void delete(DietaDiaria dieta) {
        dietaDiariaDao.delete(dieta);
    }
}
