package med.voll.api.domain.consulta;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
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

        /*Sin el metodo get final no se retorna un tipo de variable paciente o medico*/
        Paciente paciente = pacienteRepository.findById(datos.idPaciente()).get();
        Medico medico = medicoRepository.findById(datos.idMedico()).get();

        var consulta = new Consulta(null,medico,paciente, datos.fecha());
        consultaRepository.save(consulta);
    }
}
