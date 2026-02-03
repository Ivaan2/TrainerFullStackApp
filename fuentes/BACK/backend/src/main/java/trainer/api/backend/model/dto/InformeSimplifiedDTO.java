package trainer.api.backend.model.dto;

import trainer.api.backend.model.entity.enums.NivelActividad;
import trainer.api.backend.model.entity.enums.SeguimientoDieta;

/**
 * DTO simplificado para registro de informes de usuarios autónomos.
 *
 * Este DTO está diseñado específicamente para roles de USUARIO_AUTONOMO que
 * necesitan realizar un seguimiento básico de su progreso sin la complejidad
 * de las medidas corporales detalladas requeridas por entrenadores profesionales.
 *
 * Campos incluidos:
 * - fechaRegistro: Fecha del registro (yyyy-MM-dd)
 * - diasEntreno: Días de entrenamiento a la semana
 * - intensidadEntreno: Nivel de intensidad del entrenamiento
 * - peso: Peso corporal en kg
 * - seguimientoDieta: Nivel de adherencia a la dieta
 *
 * @see InformeRequestDTO Para registro completo de informes (entrenadores)
 * @see InformeInicialDTO Para primer informe de un objetivo
 */
public record InformeSimplifiedDTO(
        String fechaRegistro,        // formato yyyy-MM-dd
        Integer diasEntreno,         // días de entrenamiento a la semana (0-7)
        NivelActividad intensidadEntreno,  // nivel de intensidad del entrenamiento
        Double peso,                 // peso corporal en kg
        SeguimientoDieta seguimientoDieta  // adherencia a la dieta
) {
    /**
     * Constructor compacto con validaciones básicas.
     */
    public InformeSimplifiedDTO {
        // Validar que diasEntreno esté en rango válido
        if (diasEntreno != null && (diasEntreno < 0 || diasEntreno > 7)) {
            throw new IllegalArgumentException("Los días de entreno deben estar entre 0 y 7");
        }

        // Validar que el peso sea positivo
        if (peso != null && peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser un valor positivo");
        }
    }
}
