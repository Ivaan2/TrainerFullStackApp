package trainer.api.backend.model.dto;

import java.util.List;

/**
 * Record que representa la estructura básica de Objetivo.
 * Contiene todos los campos del objetivo actual (ObjetivoDTO).
 * Las fechas están en formato yyyy-MM-dd (compatible SQL).
 */
public record ObjetivoRecord(
        Long id,
        String descripcion,
        String fechaRegistro, // formato yyyy-MM-dd
        String fechaFin,      // formato yyyy-MM-dd
        Boolean cumplido,
        UsuarioRegistroResponseDTO usuario,
        List<InformeDTO> informes
) {}
