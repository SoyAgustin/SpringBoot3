package med.voll.api.infra.errores;

/*Excepcion lanzada cuando no se cumplan los criterios de asignacion opara una cita
* en AgendaDeConsultaService*/
public class ValidacionDeIntegridad extends RuntimeException {
    public ValidacionDeIntegridad(String s) {
        super(s);
    }
}
