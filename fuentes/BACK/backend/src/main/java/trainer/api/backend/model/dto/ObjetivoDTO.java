package trainer.api.backend.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjetivoDTO {

    private Long id;

    private String descripcion;

    private Timestamp fechaRegistro;

    private Timestamp fechaFin;

    private Boolean cumplido;

    private Long usuarioId;
}
