package trainer.api.backend.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trainer.api.backend.model.entity.DietaDiaria;
import trainer.api.backend.model.entity.NombreComida;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ComidaDTO {

    private Long id;

    private DietaDiaria dietaDiaria;

    private NombreComida nombre;

    private LocalTime hora;

    private Double aporteCalorico;

    private Double aporteProteico;

    private Double aporteGrasa;

    private Double aporteCarbohidratos;
}
