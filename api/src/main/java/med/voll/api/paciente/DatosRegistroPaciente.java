package med.voll.api.paciente;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;
import med.voll.api.medico.Especialidad;

public record DatosRegistroPaciente(
        @NotBlank
        String nombre,
        @NotBlank
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String documento_identidad,
        @NotNull
        @Valid
        DatosDireccion direccion
) { }
