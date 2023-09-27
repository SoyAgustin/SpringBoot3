package med.voll.api.medico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

//JPA Hibernate, Persistencia con:
@Table(name="medicos")
@Entity(name = "Medico")
//Lombok, genera automaticamente:
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    /*Constructor para hacer la persistencia en la base de datos
    * con los datos del médico crea un médico*/
    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.telefono = datosRegistroMedico.telefono();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion =new Direccion(datosRegistroMedico.direccion());
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if(datosActualizarMedico.nombre()!=null){
            this.nombre = datosActualizarMedico.nombre();
        }
        if(datosActualizarMedico.documento()!=null){
            this.documento = datosActualizarMedico.documento();
        }
        if(datosActualizarMedico.direccion()!=null) {
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }
    }

}
