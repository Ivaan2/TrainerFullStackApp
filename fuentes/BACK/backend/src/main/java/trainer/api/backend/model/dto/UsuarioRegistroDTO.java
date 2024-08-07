package trainer.api.backend.model.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRegistroDTO implements Serializable {

    private Long idUsuarioRegistro;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String password;
    private int edad;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Date fechaBaja;
}
