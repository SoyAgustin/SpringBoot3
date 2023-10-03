package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

/*DTO con los datos detallados después de generar una consulta médica*/
public record DatosDetalleConsulta(Long id, Long idPaciente, Long idMedico, LocalDateTime fecha) {
    public DatosDetalleConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getPaciente().getId(),consulta.getMedico().getId(),consulta.getData());
    }
}
