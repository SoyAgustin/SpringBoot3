package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

/*Las consultas se deben hacer con al menos 30 minutos de anticipacion*/
public class HorarioDeAnticipacion {
    public void validar(DatosAgendarConsulta datos) {
        var ahora = LocalDateTime.now();
        var horaDeConsulta = datos.fecha();

        Boolean diferenciaDe30min = Duration.between(ahora, horaDeConsulta).toMinutes() < 30;


        if (!diferenciaDe30min) {
            throw new ValidationException("Las consultas deben tener al menos 30 minutos de anticipacion");
        }
    }
}
