package trainer.api.backend.config.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import trainer.api.backend.model.dto.UsuarioRegistroDTO;
import trainer.api.backend.model.entity.UsuarioRegistro;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ObjetivoMapper.class})
public interface UsuarioRegistroMapper {

    // Instancia del mapper (opcional si usas Spring)
    UsuarioRegistroMapper INSTANCE = Mappers.getMapper(UsuarioRegistroMapper.class);

    // Mapear Entity → DTO
    @Mapping(target = "objetivos", source = "objetivos")
    UsuarioRegistroDTO toDTO(UsuarioRegistro entity);

    // Mapear DTO → Entity
    @Mapping(target = "objetivos", source = "objetivos")
    UsuarioRegistro toEntity(UsuarioRegistroDTO dto);

    // Mapear listas
    List<UsuarioRegistroDTO> toDTOList(List<UsuarioRegistro> entities);
    List<UsuarioRegistro> toEntityList(List<UsuarioRegistroDTO> dtos);
}
