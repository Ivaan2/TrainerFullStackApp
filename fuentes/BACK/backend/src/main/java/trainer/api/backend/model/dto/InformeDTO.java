package trainer.api.backend.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trainer.api.backend.model.entity.NivelActividad;
import trainer.api.backend.model.entity.Sexo;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class InformeDTO {

    private Long id;

    private Long objetivoId;

    private Integer edad;

    private Sexo sexo;

    private Integer altura;

    private Double peso;

    private Double porcentajeGraso;

    private Double porcentajeMusculo;

    private NivelActividad nivelActividad;

    private Double imc;

    private Double tmb;

    private Timestamp fechaRegistro;
}
