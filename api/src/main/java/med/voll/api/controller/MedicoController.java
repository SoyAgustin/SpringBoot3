package med.voll.api.controller;

import med.voll.api.medico.DatosRegistroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    /*El parametro es lo que se recibe desde el front,
    * en este caso desde Insomnia por medio de un POST
    * y se recibe en formato JSON*/
    @PostMapping
    public void registrarMedico(@RequestBody DatosRegistroMedico datosRegistroMedico){
        /*Si se ve en la clase DatosRegistroMedico
        * el mapeo que se hace es parte del patron DTO
        * Data Transfer Object
        * Parecido a las entidades con JPA*/
        System.out.println(datosRegistroMedico);
    }
}
