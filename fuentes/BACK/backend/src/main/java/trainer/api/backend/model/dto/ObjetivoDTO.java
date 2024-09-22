package trainer.api.backend.model.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjetivoDTO {

    private Long id;

    private String descripcion;

    private String fechaRegistro;

    private String fechaFin;

    private Boolean cumplido;

    private Long usuarioId;

    private List<InformeDTO> informes;
}
