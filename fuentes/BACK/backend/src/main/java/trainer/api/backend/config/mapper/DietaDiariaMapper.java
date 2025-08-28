package trainer.api.backend.config.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trainer.api.backend.model.dto.DietaDiariaDTO;
import trainer.api.backend.model.entity.DietaDiaria;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ComidaMapper.class})
public interface DietaDiariaMapper {

    // Entity -> DTO
    @Mapping(target = "informeId", source = "informe.id")
    DietaDiariaDTO toDTO(DietaDiaria entity);

    // DTO -> Entity
    @Mapping(target = "informe.id", source = "informeId")
    DietaDiaria toEntity(DietaDiariaDTO dto);

    // Listas
    List<DietaDiariaDTO> toDTOList(List<DietaDiaria> entities);

    List<DietaDiaria> toEntityList(List<DietaDiariaDTO> dtos);
}
