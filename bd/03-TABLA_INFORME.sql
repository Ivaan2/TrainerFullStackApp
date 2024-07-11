-- CREACION DE LA TABLA DE ESPECIFICACIONES DE CADA USUARIO
CREATE TABLE T_INFORME(
	ID_INFORME BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador de la tabla de informe del perfil deportista del usuario.',
	FK_OBJETIVO BIGINT NOT NULL COMMENT 'Identificador FK para la tabla objetivo',
	N_EDAD BIGINT NOT NULL COMMENT 'Edad del usuario que se somete al cambio físico',
	D_SEXO ENUM('Masculino', 'Femenino') NOT NULL COMMENT 'Define el sexo del usuario (`Masculino`, `Femenino`)',
	N_ALTURA BIGINT NOT NULL COMMENT 'Altura del usuario, en centímetros',
	N_PESO DECIMAL(5,2) NOT NULL COMMENT 'Peso del usuario, con hasta 2 decimales',
    N_CADERA DECIMAL(5,2) COMMENT 'Longitud de la cadera en cm',
    N_BICEP DECIMAL(5,2) COMMENT 'Longitud del bíceps en cm',
    N_ANTEBRAZO DECIMAL(5,2) COMMENT 'Longitud del antebrazo en cm',
    N_HOMBRO DECIMAL(5,2) COMMENT 'Longitud de los hombros en cm',
    N_PECHO DECIMAL(5,2) COMMENT 'Longitud del pecho en cm',
    N_ABDOMEN DECIMAL(5,2) COMMENT 'Longitud del abdomen en cm',
    N_CUADRICEP DECIMAL(5,2) COMMENT 'Longitud de los cuádriceps en cm',
    N_GEMELOS DECIMAL(5,2) COMMENT 'Longitud de los gemelos en cm',
    N_GLUTEO DECIMAL(5,2) COMMENT 'Longitud del glúteo en cm',
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
(FK_OBJETIVO, N_EDAD, D_SEXO, N_ALTURA, N_PESO, N_CADERA, N_BICEP, N_ANTEBRAZO, N_HOMBRO, N_PECHO, N_ABDOMEN, N_CUADRICEP, N_GEMELOS, N_GLUTEO, N_PORCENTAJE_GRASO, N_PORCENTAJE_MUSCULO, D_NIVEL_ACTIVIDAD, N_IMC, N_TMB)
VALUES
(1, 25, 'Masculino', 175, 70.00, 49.00, 31.00, 19.00, 78.20, 68.40, 49.89, 50.00, 35.00, 50.21, 15.00, 40.00, 'Moderada', 23.85, 1850.00);

SELECT * FROM t_informe;

-- DROP TABLE T_INFORME;