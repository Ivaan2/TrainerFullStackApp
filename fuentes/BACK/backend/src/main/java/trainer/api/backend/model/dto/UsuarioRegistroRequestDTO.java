package trainer.api.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * DTO para request de creación/actualización de usuario.
 * Hereda los campos comunes de UsuarioAbstractDTO y añade:
 * - rutaAvatar
 * - fechaActualizacion
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsuarioRegistroRequestDTO extends UsuarioAbstractDTO {
    private String rutaAvatar;
    private Timestamp fechaActualizacion;
}
