package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.DatosDireccion;

/*DTO para el método actualizarMedico, no es bueno retornar la enntidad, por eso se creo este
* record.*/
public record DatosRespuestaMedico(Long id, String nombre, String email, String telefono, String documento, DatosDireccion direccion) { }
