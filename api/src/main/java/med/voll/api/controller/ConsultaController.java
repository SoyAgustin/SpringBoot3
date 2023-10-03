package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/*Clase controller para agendar una consulta médica*/
@RestController
@ResponseBody
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService servicio;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos){
        System.out.println(datos);

        var response = servicio.agendar(datos);

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/cancelar")
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos){
        System.out.println(datos);
        /*Se debe hacer un tratamiento completo en el metodo cancelar
        * en alguna parte se debe agregar un metodo para actualizar el estado de las consultas*/
        response = servicio.cancelar(datos);
        return ResponseEntity.ok().build();
    }
}
