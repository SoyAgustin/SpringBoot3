package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.desafio.MotivoCancelamiento;

public record DatosCancelamientoConsulta (@NotNull Long idConsulta, @NotNull MotivoCancelamiento motivo) {
}
