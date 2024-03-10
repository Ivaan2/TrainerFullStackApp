package trainer.api.backend.model.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "T_OBJETIVO")
public class Objetivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OBJETIVO")
    private Long id;

    @Column(name = "D_DESCRIPCION", nullable = false, length = 400)
    private String descripcion;

    @Column(name = "F_FECHA_REGISTRO", nullable = false)
    private Timestamp fechaRegistro;

    @Column(name = "F_FECHA_FIN", nullable = false)
    private Timestamp fechaFin;

    @Column(name = "B_CUMPLIDO")
    private Boolean cumplido;

    @Column(name = "FK_USUARIO", nullable = false)
    private Long usuarioId;

    /*
    Caused by: java.sql.SQLException: Referencing column 'FK_USUARIO' and referenced column 'id_usuario_registro' in foreign key constraint 'FK_OBJETIVO_USUARIO' are incompatible.
    TODO: Solucionar el problema

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_REGISTRO", referencedColumnName = "ID_USUARIO_REGISTRO")
    private UsuarioRegistro usuarioRegistro;*/
}