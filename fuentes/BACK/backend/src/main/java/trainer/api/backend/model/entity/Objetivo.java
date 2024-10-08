package trainer.api.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

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

    @Column(name = "FK_USUARIO", nullable = false)
    private Long usuarioId;
}