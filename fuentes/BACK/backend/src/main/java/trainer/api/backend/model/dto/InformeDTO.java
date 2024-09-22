package trainer.api.backend.model.dto;

import lombok.*;
import trainer.api.backend.model.entity.enums.NivelActividad;
import trainer.api.backend.model.entity.enums.SeguimientoDieta;
import trainer.api.backend.model.entity.enums.Sexo;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InformeDTO {

    private Long id;

    private Long objetivoId;

    private Integer edad;

    private Sexo sexo;

    private Integer altura;

    private Double peso;

    private Double cadera;

    private Double gemelos;

    private Double cuadriceps;

    private Double abdomen;

    private Double pecho;

    private Double hombros;

    private Double antebrazo;

    private Double biceps;

    private Double gluteos;

    private Double porcentajeGraso;

    private Double porcentajeMusculo;

    private NivelActividad nivelActividad;

    private int diasEntreno;

    private SeguimientoDieta seguimientoDieta;

    private Double imc;

    private Double tmb;

    private String fechaRegistro;
}
