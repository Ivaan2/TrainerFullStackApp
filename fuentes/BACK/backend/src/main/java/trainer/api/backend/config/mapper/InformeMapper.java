package trainer.api.backend.config.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trainer.api.backend.model.dto.InformeDTO;
import trainer.api.backend.model.entity.Informe;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DietaDiariaMapper.class})
public interface InformeMapper {

    // Entity -> DTO
    @Mapping(target = "objetivoId", source = "objetivo.id") // saca el id del objetivo padre
    @Mapping(target = "fechaRegistro", source = "fechaRegistro", dateFormat = "yyyy-MM-dd")
    InformeDTO toDTO(Informe entity);

    // DTO -> Entity
    @Mapping(target = "objetivo.id", source = "objetivoId") // vuelve a setear el objetivo por id
    @Mapping(target = "fechaRegistro", source = "fechaRegistro", dateFormat = "yyyy-MM-dd")
    Informe toEntity(InformeDTO dto);

    // Listas
    List<InformeDTO> toDTOList(List<Informe> entities);

    List<Informe> toEntityList(List<InformeDTO> dtos);
}
