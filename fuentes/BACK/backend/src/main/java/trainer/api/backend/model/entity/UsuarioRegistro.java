package trainer.api.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_USUARIO_REGISTRO")
public class UsuarioRegistro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO_REGISTRO")
    private Long idUsuarioRegistro;

    @Column(name = "D_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "D_APELLIDO1", nullable = false, length = 100)
    private String apellido1;

    @Column(name = "D_APELLIDO2", length = 100)
    private String apellido2;

    @Column(name = "D_PASSWORD", length = 255, nullable = false)
    private String password;

    @Column(name = "D_EMAIL", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "N_EDAD")
    private Integer edad;

    @Enumerated(EnumType.STRING)
    @Column(name = "D_SEXO")
    private Sexo sexo;

    @Column(name = "N_ALTURA")
    private Integer altura;

    @Column(name = "N_PESO")
    private Double peso;

    @Column(name = "F_FECHA_REGISTRO", nullable = false)
    private Timestamp fechaRegistro;

    @Column(name = "F_FECHA_ACTUALIZACION")
    private Timestamp fechaActualizacion;

    @Column(name = "F_FECHA_BAJA")
    private Date fechaBaja;
}