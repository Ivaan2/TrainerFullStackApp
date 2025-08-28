package trainer.api.backend.model.dto;

import lombok.Data;
import trainer.api.backend.model.entity.enums.NombreComida;

import java.time.LocalTime;

@Data
public class ComidaDTO {
    private Long id;
    private Long dietaId;

    private NombreComida tipoComida;
    private String nombrePlato;
    private LocalTime hora;

    private Double aporteCalorico;
    private Double aporteProteico;
    private Double aporteGrasa;
    private Double aporteCarbohidratos;
}
