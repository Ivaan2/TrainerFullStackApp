package trainer.api.backend.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import trainer.api.backend.model.entity.enums.NivelActividad;
import trainer.api.backend.model.entity.enums.SeguimientoDieta;
import trainer.api.backend.model.entity.enums.Sexo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column(name = "N_BICEPS")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "D_SEGUIMIENTO_DIETA", nullable = false)
    private SeguimientoDieta seguimientoDieta;

    @Column(name = "N_DIAS_ENTRENO", nullable = false)
    private Integer diasEntreno;

    @Column(name = "N_IMC")
    private Double imc;

    @Column(name = "N_TMB")
    private Double tmb;

    @Column(name = "F_FECHA_REGISTRO", nullable = false)
    @Schema(description = "Report date", example = "20/12/2023")
    private Date fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_OBJETIVO", nullable = false)
    private Objetivo objetivo;

    @OneToMany(mappedBy = "informe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DietaDiaria> dietaDiaria = new ArrayList<>();
}