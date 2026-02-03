package trainer.api.backend.model.dto;

import trainer.api.backend.model.entity.enums.NivelActividad;
import trainer.api.backend.model.entity.enums.SeguimientoDieta;

/**
 * DTO para el primer informe que se crea al iniciar un objetivo.
 * Contiene los campos esenciales de medición corporal y fechaRegistro.
 */
public record InformeInicialDTO(
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
        Integer diasEntreno,
        String fechaRegistro // formato yyyy-MM-dd
) {}
