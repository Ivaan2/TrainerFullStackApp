package trainer.api.backend.config.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trainer.api.backend.model.dto.ComidaDTO;
import trainer.api.backend.model.entity.Comida;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComidaMapper {

    // Entity -> DTO
    @Mapping(target = "dietaId", source = "dieta.id")
    ComidaDTO toDTO(Comida entity);

    // DTO -> Entity
    @Mapping(target = "dieta.id", source = "dietaId")
    Comida toEntity(ComidaDTO dto);

    // Listas
    List<ComidaDTO> toDTOList(List<Comida> entities);

    List<Comida> toEntityList(List<ComidaDTO> dtos);
}
