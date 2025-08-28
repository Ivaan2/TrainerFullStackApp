package trainer.api.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "T_OBJETIVO")
public class Objetivo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OBJETIVO")
    private Long id;

    @Column(name = "D_DESCRIPCION", nullable = false, length = 400)
    private String descripcion;

    @Column(name = "F_FECHA_INICIO", nullable = false)
    private Timestamp fechaRegistro;

    @Column(name = "F_FECHA_FIN", nullable = false)
    private Timestamp fechaFin;

    @Column(name = "B_CUMPLIDO")
    private Boolean cumplido;

    @JoinColumn(name = "FK_USUARIO", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioRegistro usuario;

    @OneToMany(mappedBy = "objetivo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Informe> informe = new ArrayList<>();
}