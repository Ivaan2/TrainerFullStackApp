package trainer.api.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_INFORME")
public class Informe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INFORME")
    private Long id;

    @Column(name = "FK_OBJETIVO", nullable = false)
    private Long objetivoId;

    @Column(name = "N_EDAD", nullable = false)
    private Integer edad;

    @Enumerated(EnumType.STRING)
    @Column(name = "D_SEXO", nullable = false)
    private Sexo sexo;

    @Column(name = "N_ALTURA", nullable = false)
    private Integer altura;

    @Column(name = "N_PESO", nullable = false)
    private Double peso;

    @Column(name = "N_CADERA")
    private Double cadera;

    @Column(name = "N_GEMELOS")
    private Double gemelos;

    @Column(name = "N_CUADRICEP")
    private Double cuadriceps;

    @Column(name = "N_ABDOMEN")
    private Double abdomen;

    @Column(name = "N_PECHO")
    private Double pecho;

    @Column(name = "N_HOMBRO")
    private Double hombros;

    @Column(name = "N_ANTEBRAZO")
    private Double antebrazo;

    @Column(name = "N_BICEP")
    private Double biceps;

    @Column(name = "N_GLUTEO")
    private Double gluteos;

    @Column(name = "N_PORCENTAJE_GRASO")
    private Double porcentajeGraso;

    @Column(name = "N_PORCENTAJE_MUSCULO")
    private Double porcentajeMusculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "D_NIVEL_ACTIVIDAD", nullable = false)
    private NivelActividad nivelActividad;

    @Column(name = "N_IMC")
    private Double imc;

    @Column(name = "N_TMB")
    private Double tmb;

    @Column(name = "F_FECHA_REGISTRO", nullable = false)
    private Timestamp fechaRegistro;

    /*@Column(name = "ID_OBJETIVO", nullable = false)
    private Long objetivo;*/

}