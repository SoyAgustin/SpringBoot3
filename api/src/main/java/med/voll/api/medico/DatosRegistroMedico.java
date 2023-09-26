package med.voll.api.medico;

import med.voll.api.DatosDireccion;

/*Un record es un objeto inmutable que solo
* sirve para almacenar los datos para despues
* ser leídos*/
public record DatosRegistroMedico(String nombre, String email, String documento, Especialidad especialidad, DatosDireccion datosDireccion) {

}
