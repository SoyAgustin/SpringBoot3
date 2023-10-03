package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.infra.errores.ValidacionDeIntegridad;

import java.time.DayOfWeek;

public class HorarioDeFuncionamientoClinica {

    /*metodo para validar lque no se agende cita en domingo o fuera del
    * horario de atencion que es de 7:00 a 19:00 horas*/
    public void validar(DatosAgendarConsulta datos){

        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var  antesDeApertura=datos.fecha().getHour()<7;
        var despuesDeCierre=datos.fecha().getHour()>19;
        if(domingo || antesDeApertura || despuesDeCierre){
            throw new ValidationException("El horario de la clinica es de lunes a sabado de 7:00 a 19:00 hoas");
        }

    }
}
