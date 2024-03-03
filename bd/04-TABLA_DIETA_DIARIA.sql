-- CREACION DE LA TABLA DIETA
CREATE TABLE T_DIETA_DIARIA(
	ID_DIETA INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador de la información de la dieta diaria.',
    FK_INFORME INT NOT NULL COMMENT 'Identificador de la tabla informe.',
    D_DIA ENUM('L', 'M', 'X', 'J', 'V', 'S', 'D') NOT NULL COMMENT 'Nombre del día de la semana',
    N_COMIDAS_DIARIAS INT NOT NULL COMMENT 'Número de comidas diaria para la dieta.',
    N_CALORIAS_TOTALES DECIMAL(6,2) NOT NULL COMMENT 'Objetivo calórico dirario',
    N_REQ_PROTE DECIMAL(5,2) NOT NULL COMMENT 'Establece el requerimiento proteico en gramos.',
    N_REQ_GRASA DECIMAL(5,2) NOT NULL COMMENT 'Establece el requerimiento de grasa en gramos.',
    N_REQ_CARB DECIMAL(5,2) NOT NULL COMMENT 'Establece el requerimiento de carbohidratos en gramos.',
    N_REQ_AGUA DECIMAL(2,1) NOT NULL COMMENT 'Establece el requerimiento de litros de agua diarios.',
    CONSTRAINT FK_DIETA_INFORME FOREIGN KEY (FK_INFORME) REFERENCES T_INFORME(ID_INFORME)
);

INSERT INTO T_DIETA_DIARIA
(FK_INFORME, D_DIA, N_COMIDAS_DIARIAS, N_CALORIAS_TOTALES, N_REQ_PROTE, N_REQ_GRASA, N_REQ_CARB, N_REQ_AGUA)
VALUES
(1, 'L', 5, 2500.00, 150.00, 70.00, 300.00, 2.5);



-- DROP TABLE t_dieta_diaria;