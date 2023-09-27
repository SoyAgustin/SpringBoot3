package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosListadoMedico;
import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    /*Esta anotacion  'conecta' con el repositorio MedicoRepository
    que es en donde  se sustituye el controlador DAO.'*/
    @Autowired // No es recomendable usar esta anotacion aqui, ya que en pruebas unitarias puede causar problemas
    private MedicoRepository medicoRepository;
    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){
       medicoRepository.save(new Medico(datosRegistroMedico));
    }

    /*Usando query params directamennte e la url se
    * puedenn cambiar los parametros de paginacion.
    * por ejemplo endpoint/?size=1&page=2
    * retorna 1 solo registro por pagina, en este caso
    * seria el segundo registro ya que por pagina se
    * muestra 1
    *
    * También se pueden ingresar esos parámetros por defult con la anotacion
    * @PageableDefault()*/

    @GetMapping
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size= 2)  Pageable paginacion){
        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
    }
}
