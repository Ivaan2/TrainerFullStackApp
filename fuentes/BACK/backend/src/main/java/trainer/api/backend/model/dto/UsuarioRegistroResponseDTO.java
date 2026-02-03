package trainer.api.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.util.Date;

/**
 * DTO para respuestas que incluyen datos de usuario.
 * Hereda los campos comunes de UsuarioAbstractDTO y añade:
 * - idUsuarioRegistro
 * - nombreUsuario
 * - fechaActualizacion
 * - fechaBaja
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsuarioRegistroResponseDTO extends UsuarioAbstractDTO {
    private Long idUsuarioRegistro;
    private String nombreUsuario;
    private Timestamp fechaActualizacion;
    private Date fechaBaja;
}
