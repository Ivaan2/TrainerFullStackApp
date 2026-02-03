package trainer.api.backend.model.dto;

import trainer.api.backend.model.entity.enums.NivelActividad;
import trainer.api.backend.model.entity.enums.SeguimientoDieta;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO para request de creación/actualización de informe.
 * Incluye todos los campos de InformeAbstractDTO más campos adicionales:
 * - fechaRegistro (yyyy-MM-dd)
 * - lista de dietaDiaria inicializada vacía
 * - imc, tmb calculados
 * - id, objetivoId para referencias
 */
public record InformeRequestDTO(
        Long id,
        Long objetivoId,
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
        String fechaRegistro, // formato yyyy-MM-dd
        List<DietaDiariaDTO> dietaDiaria,
        Double imc,
        Double tmb
) {
    // Constructor canónico compacto: inicializa dietaDiaria si es null
    public InformeRequestDTO {
        if (dietaDiaria == null) {
            dietaDiaria = new ArrayList<>();
        }
    }
}
