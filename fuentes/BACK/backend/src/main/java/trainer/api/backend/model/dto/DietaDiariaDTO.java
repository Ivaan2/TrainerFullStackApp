package trainer.api.backend.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trainer.api.backend.model.entity.DiaSemana;

@Getter
@Setter
@NoArgsConstructor
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
}
