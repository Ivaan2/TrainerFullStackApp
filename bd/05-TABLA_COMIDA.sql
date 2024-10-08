CREATE TABLE T_COMIDA(
	ID_COMIDA BIGINT auto_increment PRIMARY KEY COMMENT 'Identificador de la comida asociada a una dieta',
    FK_DIETA BIGINT NOT NULL COMMENT 'Identificador de la dieta semanal',
    D_NOMBRE ENUM('DESAYUNO', 'MEDIA_MANANA', 'ALMUERZO', 'MERIENDA', 'CENA') NOT NULL COMMENT 'Nombre del plato.',
    F_HORA TIME NOT NULL COMMENT 'Horario de ingesta del plato. (Formato HH:mm)',
    N_APORTE_CALORICO DECIMAL(6,2) NOT NULL COMMENT 'Aporte calórico.',
    N_APORTE_PROTE DECIMAL(5,2) NOT NULL COMMENT 'Aporte proteico en gramos.',
    N_APORTE_GRASA DECIMAL(5,2) NOT NULL COMMENT 'Aporte de grasa en gramos.',
    N_APORTE_CARB DECIMAL(5,2) NOT NULL COMMENT 'Aporte de carbohidratos en gramos.',
    CONSTRAINT FK_COMIDA_DIETA FOREIGN KEY (FK_DIETA) REFERENCES T_DIETA_DIARIA(ID_DIETA)
) COMMENT = 'Información correspondiente a cada una de las comidas de una dieta.';

INSERT INTO T_COMIDA
(FK_DIETA, D_NOMBRE, F_HORA, N_APORTE_CALORICO, N_APORTE_PROTE, N_APORTE_GRASA, N_APORTE_CARB)
VALUES
(1, 'DESAYUNO', '08:00', 500.00, 30.00, 15.00, 60.00),
(1, 'MEDIA_MANANA', '11:00', 300.00, 20.00, 10.00, 40.00),
(1, 'ALMUERZO', '14:00', 700.00, 40.00, 20.00, 100.00),
(1, 'MERIENDA', '17:00', 400.00, 25.00, 15.00, 50.00),
(1, 'CENA', '20:00', 600.00, 35.00, 10.00, 50.00);

commit;
-- DROP TABLE t_comida;