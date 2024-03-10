-- CREACION DE LA TABLA DE ESPECIFICACIONES DE CADA USUARIO
CREATE TABLE T_INFORME(
	ID_INFORME BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador de la tabla de informe del perfil deportista del usuario.',
	FK_OBJETIVO BIGINT NOT NULL COMMENT 'Identificador FK para la tabla objetivo',
	N_EDAD BIGINT NOT NULL COMMENT 'Edad del usuario que se somete al cambio físico',
	D_SEXO ENUM('Masculino', 'Femenino') NOT NULL COMMENT 'Define el sexo del usuario (`Masculino`, `Femenino`)',
	N_ALTURA BIGINT NOT NULL COMMENT 'Altura del usuario, en centímetros',
	N_PESO DECIMAL(5,2) NOT NULL COMMENT 'Peso del usuario, con hasta 2 decimales',
	N_PORCENTAJE_GRASO DECIMAL(4,2) COMMENT 'Porcentaje graso corporal del usuario. Opcional',
	N_PORCENTAJE_MUSCULO DECIMAL(4,2) COMMENT 'Porcentaje de masa muscular del usuario. Opcional',
	D_NIVEL_ACTIVIDAD ENUM('Sedentario', 'Poca actividad física', 'Moderada', 'Alta', 'Muy Alta') NOT NULL COMMENT 'Indica el nivel de actividad física del usuario.',
	N_IMC DECIMAL(4,2) DEFAULT NULL COMMENT 'Índice de Masa Corporal. Se calcula una vez tenemos los datos recolectados, no se aporta en la creacion de la tabla.',
	N_TMB DECIMAL(4,2) DEFAULT NULL COMMENT 'Tasa Metabólica Basal. Se calcula una vez tenemos los datos recolectados, no se aporta en la creacion de la tabla.',
	F_FECHA_REGISTRO TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro del informe de usuario',
	CONSTRAINT FK_INFORME_OBJETIVO FOREIGN KEY (FK_OBJETIVO) REFERENCES T_OBJETIVO(ID_OBJETIVO)
) COMMENT = 'Tabla de creación periódica que mida el informe de progreso de un usuario dentro de un objetivo en concreto.';

-- INSERT
INSERT INTO T_INFORME
(FK_OBJETIVO, N_EDAD, D_SEXO, N_ALTURA, N_PESO, N_PORCENTAJE_GRASO, N_PORCENTAJE_MUSCULO, D_NIVEL_ACTIVIDAD)
VALUES
(1, 25, 'Masculino', 175, 70.00, 15.00, 40.00, 'Moderada');

-- DROP TABLE T_INFORME;