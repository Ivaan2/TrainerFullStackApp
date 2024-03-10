package trainer.api.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "T_DIETA_DIARIA")
public class DietaDiaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DIETA")
    private Long id;

    @Column(name = "FK_INFORME", nullable = false)
    private Long informeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "D_DIA", nullable = false)
    private DiaSemana dia;

    @Column(name = "N_COMIDAS_DIARIAS", nullable = false)
    private Integer comidasDiarias;

    @Column(name = "N_CALORIAS_TOTALES", nullable = false)
    private Double caloriasTotales;

    @Column(name = "N_REQ_PROTE", nullable = false)
    private Double requerimientoProteico;

    @Column(name = "N_REQ_GRASA", nullable = false)
    private Double requerimientoGrasa;

    @Column(name = "N_REQ_CARB", nullable = false)
    private Double requerimientoCarbohidratos;

    @Column(name = "N_REQ_AGUA", nullable = false)
    private Double requerimientoAgua;

    /*@OneToMany(mappedBy = "dietaDiaria")
    private List<Comida> comidas;*/
}
