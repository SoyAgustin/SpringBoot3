package med.voll.api.medico;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;

/*Un record es un objeto inmutable que solo
* sirve para almacenar los datos para despues
* ser leídos*/
public record DatosRegistroMedico(
        /*Estas validaciones son de la dependencia Validations
        * NotBlank ya hace lo de NotNull pero tambie verifica que no sea ""*/
        @NotBlank
        String nombre,
        @NotBlank
        String email,
        @NotBlank
        @Pattern(regexp = "\\d(4,6)")
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull
        @Valid
        DatosDireccion direccion
) { }
