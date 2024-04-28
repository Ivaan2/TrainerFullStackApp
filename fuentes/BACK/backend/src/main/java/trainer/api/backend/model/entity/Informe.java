package trainer.api.backend.model.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
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

    @Column(name = "N_CADERA", nullable = false)
    private Double cadera;

    @Column(name = "N_GEMELOS", nullable = false)
    private Double gemelos;

    @Column(name = "N_CUADRICEP", nullable = false)
    private Double cuadriceps;

    @Column(name = "N_ABDOMEN", nullable = false)
    private Double abdomen;

    @Column(name = "N_PECHO", nullable = false)
    private Double pecho;

    @Column(name = "N_HOMBRO", nullable = false)
    private Double hombros;

    @Column(name = "N_ANTEBRAZO", nullable = false)
    private Double antebrazo;

    @Column(name = "N_BICEP", nullable = false)
    private Double biceps;

    @Column(name = "N_GLUTEO", nullable = false)
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