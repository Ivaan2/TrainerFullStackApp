package trainer.api.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import trainer.api.backend.model.entity.enums.Sexo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRegistroDTO {

    private Long idUsuarioRegistro;

    private String nombreUsuario;

    private String nombre;

    private String apellido1;

    private String apellido2;

    // Para requests de creación o actualización
    private String password;

    private String email;

    private String rutaAvatar;

    private Date fechaNacimiento;

    private String pais;

    private Sexo sexo;

    private Timestamp fechaRegistro;

    private Timestamp fechaActualizacion;

    private Date fechaBaja;

    // Opcional: incluir lista de objetivos si se necesita enviar al frontend
    private List<ObjetivoDTO> objetivos;
}

