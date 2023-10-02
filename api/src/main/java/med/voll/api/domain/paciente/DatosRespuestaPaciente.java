package med.voll.api.domain.paciente;

import med.voll.api.domain.direccion.DatosDireccion;

/*DTO para el método actualizarMedico, no es bueno retornar la enntidad, por eso se creo este
* record.*/
public record DatosRespuestaPaciente(Long id, String nombre, String email, String telefono, DatosDireccion direccion) { }
