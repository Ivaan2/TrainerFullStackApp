package trainer.api.backend.model.dto;

import lombok.*;
import trainer.api.backend.model.entity.enums.DiaSemana;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
