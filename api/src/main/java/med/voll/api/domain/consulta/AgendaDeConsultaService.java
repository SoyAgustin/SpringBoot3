package med.voll.api.domain.consulta;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*En esta clase vamos a colocar las reglas de negocio
* las validaciones para que agendar una consulta sea posible*/
@Service
public class AgendaDeConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    public void agendar(@Valid DatosAgendarConsulta datos){
        /*dos formas de buscar por id y retornar booleano en caso de existir
        * .isPresent() o existByNombre()*/
        if(pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("Este Id para el paciente no fue encontrado");
        }

        if (datos.idMedico()!=null && medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("Este Id para el medico no fue encontrado");

        }

        /*Sin el metodo get final no se retorna un tipo de variable paciente o medico*/
        Paciente paciente = pacienteRepository.findById(datos.idPaciente()).get();

        Medico medico = seleccionarMedico(datos);

        var consulta = new Consulta(null,medico,paciente, datos.fecha());
        consultaRepository.save(consulta);
    }

    /*La regla de negocio dice que si el medico no se indica explicitamente se debe
    * escoger un medico de forma aleatoria siempre y cuando el medico y el paciente sean activos
    * tambien considerando disponibilidad en la fecha y hora además de la especialidad adecuada*/
    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if(datos.idMedico()!=null){//en caso de que si se proporcione el id del medico se regresa el mismo médico
            return medicoRepository.getReferenceById(datos.idMedico());
        }

        //validaciond de la especialidad
        if(datos.especialidad()==null){
            throw new ValidacionDeIntegridad("debe seleccionar una especialidad para el medico");
        }

        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(),datos.fecha());
    }
}
