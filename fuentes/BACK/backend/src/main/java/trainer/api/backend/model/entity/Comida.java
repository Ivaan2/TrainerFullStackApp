package trainer.api.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import trainer.api.backend.model.entity.enums.NombreComida;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "T_COMIDA")
public class Comida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMIDA")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_DIETA", nullable = false)
    private DietaDiaria dietaDiaria;

    @Enumerated(EnumType.STRING)
    @Column(name = "D_NOMBRE", nullable = false)
    private NombreComida tipoComida;

    @Column(name = "D_NOMBRE_PLATO", nullable = false)
    private String nombrePlato;

    @Column(name = "F_HORA", nullable = false)
    private LocalTime hora;

    @Column(name = "N_APORTE_CALORICO", nullable = false)
    private Double aporteCalorico;

    @Column(name = "N_APORTE_PROTE", nullable = false)
    private Double aporteProteico;

    @Column(name = "N_APORTE_GRASA", nullable = false)
    private Double aporteGrasa;

    @Column(name = "N_APORTE_CARB", nullable = false)
    private Double aporteCarbohidratos;

    /*@ManyToOne
    @Column(name = "ID_DIETA")
    private DietaDiaria dietaDiaria;*/
}