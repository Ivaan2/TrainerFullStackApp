package trainer.api.backend.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import trainer.api.backend.model.entity.enums.Sexo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public abstract class UsuarioAbstractDTO {
    protected String nombre;
    protected String apellido1;
    protected String apellido2;

    protected String email;
    protected Date fechaNacimiento;
    protected String pais;
    protected Sexo sexo;
    protected Timestamp fechaRegistro;

    protected List<ObjetivoDTO> objetivos = new ArrayList<>();
}
