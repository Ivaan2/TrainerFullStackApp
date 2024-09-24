package trainer.api.backend.model.dto;

import lombok.*;
import trainer.api.backend.model.entity.enums.Sexo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRegistroDTO implements Serializable {

    private Integer idUsuarioRegistro;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String password;
    private Date fechaNacimiento;
    private String nombreUsuario;
    private String rutaAvatar;
    private String pais;
    private Sexo sexo;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Date fechaBaja;
}
