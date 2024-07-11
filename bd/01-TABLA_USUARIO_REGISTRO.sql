-- CREACION DE LA TABLA USUARIO DE REGISTRO
CREATE TABLE T_USUARIO_REGISTRO(
    ID_USUARIO_REGISTRO BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único de la tabla de usuario destinada al registro',
    D_NOMBRE VARCHAR(100) NOT NULL COMMENT 'Nombre del usuario',
    D_APELLIDO1 VARCHAR(100) NOT NULL COMMENT 'Primer apellido del usuario',
    D_APELLIDO2 VARCHAR(100) COMMENT 'Segundo apellido del usuario, opcional',
    D_EMAIL VARCHAR(255) NOT NULL UNIQUE COMMENT 'Dirección e-mail del usuario, debe ser única',
    D_PASSWORD VARCHAR(255) NOT NULL COMMENT 'Contraseña de nuestro usuario',
    N_EDAD DECIMAL(3,0) COMMENT 'Edad del usuario',
    F_FECHA_REGISTRO TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora a la que el usuario se registra, con marca de tiempo automática',
    F_FECHA_ACTUALIZACION TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de la última actualización del registro, se actualiza automáticamente',
    F_FECHA_BAJA DATE COMMENT 'Fecha en la que se da de baja el usuario, opcional'
) COMMENT = 'Tabla para almacenar información de los usuarios. No contiene datos relativos a la dieta.';

INSERT INTO T_USUARIO_REGISTRO
(D_NOMBRE, D_APELLIDO1, D_APELLIDO2, D_EMAIL, D_TELEFONO, F_FECHA_BAJA)
VALUES
('Mario', 'Gonzáles', 'Labrador', 'mario.labrador@example.com', '666123987', NULL),
('María', 'López', 'Fernández', 'maria.lopez@example.com', '678678678', NULL);

-- DROP TABLE t_usuario_registro;

select * from t_usuario_registro;
select * from t_objetivo o where o.FK_USUARIO IN (1, 2, 3); -- 1 4 6
select * from t_informe i where i.FK_OBJETIVO IN (1, 4, 6); -- 1 2
select * from t_dieta_diaria d where d.FK_INFORME in (1, 2); -- 1 2 3 4 5 6 7 8
select * from t_comida c where c.FK_DIETA IN (1);

commit;