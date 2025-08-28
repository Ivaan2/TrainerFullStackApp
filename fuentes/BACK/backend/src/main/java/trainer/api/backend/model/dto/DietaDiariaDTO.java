package trainer.api.backend.model.dto;

import lombok.Data;
import trainer.api.backend.model.entity.enums.DiaSemana;

import java.util.List;

@Data
public class DietaDiariaDTO {
    private Long id;
    private Long informeId;

    private DiaSemana dia;
    private Integer comidasDiarias;
    private Double caloriasTotales;

    private Double requerimientoProteico;
    private Double requerimientoGrasa;
    private Double requerimientoCarbohidratos;
    private Double requerimientoAgua;

    private List<ComidaDTO> comidas;
}
