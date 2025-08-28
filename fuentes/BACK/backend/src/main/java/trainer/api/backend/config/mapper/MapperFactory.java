package trainer.api.backend.config.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
@Setter
public class MapperFactory {

    private final UsuarioRegistroMapper usuarioRegistroMapper;
    private final ObjetivoMapper objetivoMapper;
    private final InformeMapper informeMapper;
    private final DietaDiariaMapper dietaDiariaMapper;
    private final ComidaMapper comidaMapper;

}
