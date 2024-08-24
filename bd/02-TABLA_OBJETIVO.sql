-- CREACION DE LA TABLA DE OBJETIVO DEL PERFIL EN CONCRETO
CREATE TABLE T_OBJETIVO(
	ID_OBJETIVO BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador del objetivo físico de un usuario',
    D_DESCRIPCION VARCHAR(400) NOT NULL COMMENT 'Descripción del objetivo establecido',
    F_FECHA_REGISTRO TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de generación del objetivo marcado',
    F_FECHA_FIN TIMESTAMP NOT NULL COMMENT 'Fecha establecida para la finalización del período',
    B_CUMPLIDO BOOLEAN DEFAULT NULL COMMENT 'Marca si el objetivo ha sido cumplido tras la finalización del período. Opcional',
    FK_USUARIO BIGINT NOT NULL COMMENT 'Identificador FK de la tabla usario registrado.',
    CONSTRAINT FK_OBJETIVO_USUARIO FOREIGN KEY (FK_USUARIO) REFERENCES T_USUARIO_REGISTRO(ID_USUARIO_REGISTRO)
) COMMENT = 'Almacena el objetivo de una persona en un marco temporal prestablecido.';

INSERT INTO T_OBJETIVO (
    D_DESCRIPCION, 
    F_FECHA_FIN, 
    FK_USUARIO
) VALUES (
    'Perder 10kg en 6 meses',
    '2024-08-31 00:00:00',
    (select u.ID_USUARIO_REGISTRO from t_usuario_registro u where u.D_EMAIL = 'rosa@prueba.com')
);

SELECT * FROM t_objetivo WHERE t_objetivo.FK_USUARIO = 4;

-- DROP TABLE T_OBJETIVO;
commit;