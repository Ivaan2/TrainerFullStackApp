package trainer.api.backend.model.dto;

import trainer.api.backend.model.entity.enums.NivelActividad;
import trainer.api.backend.model.entity.enums.SeguimientoDieta;

/**
 * Record que contiene únicamente los campos básicos del informe:
 * peso, medidas corporales, porcentajes y datos de actividad/dieta
 */
public record InformeAbstractDTO(
        Double peso,
        Double cadera,
        Double gemelos,
        Double cuadriceps,
        Double abdomen,
        Double pecho,
        Double hombros,
        Double antebrazo,
        Double biceps,
        Double gluteos,
        Double porcentajeGraso,
        Double porcentajeMusculo,
        NivelActividad nivelActividad,
        SeguimientoDieta seguimientoDieta,
        Integer diasEntreno
) {}
