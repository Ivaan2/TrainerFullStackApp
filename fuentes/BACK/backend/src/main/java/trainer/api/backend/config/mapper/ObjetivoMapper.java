package trainer.api.backend.config.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trainer.api.backend.model.dto.ObjetivoDTO;
import trainer.api.backend.model.entity.Objetivo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", uses = {InformeMapper.class})
public interface ObjetivoMapper {

    @Mapping(target = "fechaRegistro", source = "fechaRegistro", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "fechaFin", source = "fechaFin", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "informes", source = "informe")
    @Mapping(target = "usuario", ignore = true) // fin del bucle
    ObjetivoDTO toDTO(Objetivo entity);

    @Mapping(target = "fechaRegistro", source = "fechaRegistro", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "fechaFin", source = "fechaFin", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "informe", source = "informes")
    @Mapping(target = "usuario", ignore = true) // fin del bucle
    Objetivo toEntity(ObjetivoDTO dto);

    List<ObjetivoDTO> toDTOList(List<Objetivo> entities);

    List<Objetivo> toEntityList(List<ObjetivoDTO> dtos);

    // Métodos de conversión personalizados
    default String map(Timestamp value) {
        if (value == null) return null;
        return value.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    default Timestamp map(String value) {
        if (value == null) return null;
        LocalDate localDate = LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Timestamp.valueOf(localDate.atStartOfDay());
    }
}
