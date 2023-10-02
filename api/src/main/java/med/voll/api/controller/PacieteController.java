package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.paciente.DatosRespuestaPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacieteController {

    @Autowired
    private PacienteRepository pacienteRepository;
    @PostMapping
    public void registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente){
       pacienteRepository.save(new Paciente(datosRegistroPaciente));
    }

    @GetMapping
    public Page<DatosListaPaciente> listarPaciente(@PageableDefault(sort={"nombre"})Pageable paginacion){
        return pacienteRepository.findAll(paginacion).map(DatosListaPaciente::new);
    }

    @PutMapping
    @Transactional
    /*Ahora se retornna un 200 ok pero tambien se retornnan los datos actualizados*/
    public ResponseEntity actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente){
        Paciente paciente = pacienteRepository.getReferenceById(datosActualizarPaciente.id());
        paciente.actualizarDatos(datosActualizarPaciente);
        return ResponseEntity.ok(new DatosRespuestaPaciente(
                        paciente.getId(),
                        paciente.getNombre(),
                        paciente.getEmail(),
                        paciente.getTelefono(),
                        new DatosDireccion(
                                paciente.getDireccion().getCalle(),
                                paciente.getDireccion().getDistrito(),
                                paciente.getDireccion().getCiudad(),
                                paciente.getDireccion().getNumero(),
                                paciente.getDireccion().getComplemento()
                        )
                )
        );
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity eliminarPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DatosRespuestaPaciente> retornnarDatosPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        var datosPaciente =new DatosRespuestaPaciente(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                new DatosDireccion(
                        paciente.getDireccion().getCalle(),
                        paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento()
                )
        );
        return ResponseEntity.ok(datosPaciente);
    }
}
