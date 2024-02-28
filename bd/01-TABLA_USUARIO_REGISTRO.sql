-- CREACION DE LAS TABLAS
CREATE TABLE T_USUARIO_REGISTRO(
    ID_USUARIO_REGISTRO INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único de la tabla de usuario destinada al registro',
    D_NOMBRE VARCHAR(100) NOT NULL COMMENT 'Nombre del usuario',
    D_APELLIDO1 VARCHAR(100) NOT NULL COMMENT 'Primer apellido del usuario',
    D_APELLIDO2 VARCHAR(100) COMMENT 'Segundo apellido del usuario, opcional',
    D_EMAIL VARCHAR(255) NOT NULL UNIQUE COMMENT 'Dirección e-mail del usuario, debe ser única',
    N_TELEFONO VARCHAR(15) COMMENT 'Número de teléfono móvil del usuario, incluyendo código de país',
    F_FECHA_REGISTRO TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora a la que el usuario se registra, con marca de tiempo automática',
    F_FECHA_ACTUALIZACION TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de la última actualización del registro, se actualiza automáticamente',
    F_FECHA_BAJA DATE COMMENT 'Fecha en la que se da de baja el usuario, opcional'
) COMMENT = 'Tabla para almacenar información de los usuarios. No contiene datos relativos a la dieta.';

SELECT * FROM T_USUARIO_REGISTRO;

INSERT INTO T_USUARIO_REGISTRO
(D_NOMBRE, D_APELLIDO1, D_APELLIDO2, D_EMAIL, N_TELEFONO, F_FECHA_BAJA)
VALUES
('Mario', 'Gonzáles', 'Labrador', 'mario.labrador@example.com', '666123987', NULL);
INSERT INTO T_USUARIO_REGISTRO
(ID_USUARIO_REGISTRO, D_NOMBRE, D_APELLIDO1, D_APELLIDO2, D_EMAIL, N_TELEFONO, F_FECHA_BAJA)
VALUES
(NULL, 'María', 'López', 'Fernández', 'maria.lopez@example.com', '678678678', NULL);
