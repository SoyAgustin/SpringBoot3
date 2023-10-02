package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/*DTO con los datos para agendar una consulta médica*/
public record DatosAgendarConsulta (Long id,@NotNull Long idPaciente, Long idMedico, @NotNull @Future LocalDateTime fecha){
}
