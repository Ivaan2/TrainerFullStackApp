package trainer.api.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
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

    @Override
    public List<Objetivo> findListByUserId(Integer id) {
        return objetivoDao.findListByUserId(id);
    }

    @Override
    public Objetivo findLastByUserId(Integer id) {
        return objetivoDao.findLastByUserId(id, PageRequest.of(0, 1)).get(0);

    }

    @Override
    public Objetivo save(ObjetivoDTO objetivoDto) {
        Objetivo objetivo = Objetivo.builder()
                .cumplido(objetivoDto.getCumplido())
                .descripcion(objetivoDto.getDescripcion())
                .fechaFin(dateToTimestamp(objetivoDto.getFechaFin()))
                .fechaRegistro(dateToTimestamp(objetivoDto.getFechaRegistro()))
                .id(objetivoDto.getId())
                .usuarioId(objetivoDto.getUsuarioId())
                .build();
        return objetivoDao.save(objetivo);
    }

    private static Timestamp dateToTimestamp(String fecha) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(fecha, dtf);
        LocalDateTime localDateTime = localDate.atStartOfDay();

        // Convertir LocalDateTime a Timestamp
        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public Objetivo findById(Long id) {
        return objetivoDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Objetivo objetivo) {
        objetivoDao.delete(objetivo);
    }

    @Override
    public boolean existsById(Long id) {
        return objetivoDao.existsById(id);
    }
}
