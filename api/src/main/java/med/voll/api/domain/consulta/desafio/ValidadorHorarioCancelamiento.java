package med.voll.api.domain.consulta.desafio;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioCancelamiento implements ValidadorCancelamientoDeConsulta {
    private ConsultaRepository repository;
    @Override
    public void validar(DatosCancelamientoConsulta datos) {
        var consulta = repository.getReferenceById(datos.idConsulta());
        var ahora= LocalDateTime.now();
        var diferenciaEnHoras= Duration.between(ahora,consulta.getData()).toHours();

        if(diferenciaEnHoras<24){
            throw new ValidationException("Soilo se pueden cancelar consultas con un minimo de 24 horas");
        }
    }
}
