package trainer.api.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjetivoAggregateDto {
    private Long id;
    private String descripcion;
    private String fechaRegistro;
    private String fechaFin;
    private Boolean cumplido;
    private UsuarioRegistroResponseDTO usuario;
    private List<InformeDTO> informes;

    // Campo adicional para el primer informe
    private InformeInicialDTO informeInicialDto;
}
