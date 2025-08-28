package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import trainer.api.backend.config.mapper.MapperFactory;
import trainer.api.backend.config.mapper.ObjetivoMapper;
import trainer.api.backend.config.mapper.UsuarioRegistroMapper;
import trainer.api.backend.model.dao.IObjetivoDao;
import trainer.api.backend.model.dto.ObjetivoDTO;
import trainer.api.backend.model.entity.Objetivo;
import trainer.api.backend.service.IObjetivo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class ObjetivoImpl implements IObjetivo {

    private final IObjetivoDao objetivoDao;
    private final MapperFactory mapperFactory;

    @Override
    public List<ObjetivoDTO> findListByUserId(Long id) {
        List<Objetivo> listByUserId = objetivoDao.findListByUserId(id);
        return objetivoMapper().toDTOList(listByUserId);
    }

    private ObjetivoMapper objetivoMapper() {
        return mapperFactory.getObjetivoMapper();
    }

    private UsuarioRegistroMapper usuarioRegistroMapper() {
        return mapperFactory.getUsuarioRegistroMapper();
    }

    @Override
    public ObjetivoDTO findLastByUserId(Long id) {
        List<Objetivo> ultimoObjetivo = objetivoDao.findLastByUserId(id, PageRequest.of(0, 1));
        return (ultimoObjetivo == null || ultimoObjetivo.isEmpty())
                ? null
                : objetivoMapper().toDTO(ultimoObjetivo.get(0));
    }

    @Override
    public ObjetivoDTO save(ObjetivoDTO objetivoDto) {
        Objetivo objetivo = objetivoMapper().toEntity(objetivoDto);

        // conversión de fechas (DTO tiene String, entidad necesita Timestamp)
        if (objetivoDto.getFechaFin() != null) {
            objetivo.setFechaFin(dateToTimestamp(objetivoDto.getFechaFin()));
        }
        if (objetivoDto.getFechaRegistro() != null) {
            objetivo.setFechaRegistro(dateToTimestamp(objetivoDto.getFechaRegistro()));
        }

        // conversión de usuarioId (DTO Long → entidad Integer)
        if (objetivoDto.getUsuario().getIdUsuarioRegistro() != null) {
            objetivo.setUsuario(usuarioRegistroMapper().toEntity(objetivoDto.getUsuario()));
        }

        Objetivo saved = objetivoDao.save(objetivo);
        return objetivoMapper().toDTO(saved);
    }

    private static Timestamp dateToTimestamp(String fecha) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(fecha, dtf);
        LocalDateTime localDateTime = localDate.atStartOfDay();

        // Convertir LocalDateTime a Timestamp
        return Timestamp.valueOf(localDateTime);
    }

    public ObjetivoDTO findById(Long id) {
        return objetivoDao.findById(id)
                .map(objetivoMapper()::toDTO)
                .orElse(null);
    }

    @Override
    public void delete(ObjetivoDTO objetivoDto) {
        Objetivo objetivo = objetivoMapper().toEntity(objetivoDto);
        objetivoDao.delete(objetivo);
    }

    @Override
    public boolean existsById(Long id) {
        return objetivoDao.existsById(id);
    }
}
