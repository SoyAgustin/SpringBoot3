package med.voll.api.domain.paciente;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.Direccion;


@Table(name="pacientes")//nommbre que tiene la tabla en la db
@Entity(name = "Paciente")//nombre igual al de la clase
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento_identidad;
    private Boolean activo=true;

    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datosRegistroPaciente) {
        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.telefono = datosRegistroPaciente.telefono();
        this.documento_identidad = datosRegistroPaciente.documento_identidad();
        this.direccion =new Direccion(datosRegistroPaciente.direccion());
    }

    public void actualizarDatos(DatosActualizarPaciente datosActualizarPaciente) {
        if(datosActualizarPaciente.nombre()!=null){
            this.nombre = datosActualizarPaciente.nombre();
        }
        if(datosActualizarPaciente.documento()!=null){
            this.documento_identidad = datosActualizarPaciente.documento();
        }
        if(datosActualizarPaciente.direccion()!=null) {
            this.direccion = direccion.actualizarDatos(datosActualizarPaciente.direccion());
        }if(datosActualizarPaciente.telefono()!=null) {
            this.telefono = datosActualizarPaciente.telefono();
        }

    }

    public void desactivarPaciente() {
        this.activo = false;
    }
}
