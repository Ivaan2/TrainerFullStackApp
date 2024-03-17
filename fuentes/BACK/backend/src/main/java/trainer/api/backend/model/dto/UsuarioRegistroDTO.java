package trainer.api.backend.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRegistradoDTO {

    private Long idUsuarioRegistro;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String email;

    private String telefono;

    private Timestamp fechaRegistro;

    private Timestamp fechaActualizacion;

    private Date fechaBaja;
}
