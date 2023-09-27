package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
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
       /*En este caso no es necesaria la anotacion Transactional porque se esta
       * llamando directamente al repositorio y guardando, es decir se usa directamente
       * JPA, en caso de que no sea así es necesaria la anotación Transactional*/
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
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        /*Se pueden hacer consultas dinámicas con findBy"Nombre de la columna""Valor"*/
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
    }

    /*Creamos un nuevo DTO para los datos actualizables, ya que la especialidad
    * el email y el documento no pueden ser actualizados*/
    @PutMapping
    @Transactional //Muy importante esta anotacion, ya que es la que hace que se actualice la db
    //En caso de que ocurra un error con Transactional se hace un rollback
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
    Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
    medico.actualizarDatos(datosActualizarMedico);
    }


    /*DELETE completo de la base de datos.
    *Para el delete se usan variable paths, la url con path final id
    * va a eliminar de la db al médico con dicho id*/
    /*
    @DeleteMapping(path = "/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }
    */

    //DELETE lógico se mantienen los registros pero cambia activo a false.
    /*Para el delete se usan variable paths, la url con path final id
     * va a eliminar de la db al médico con dicho id*/
    @DeleteMapping(path = "/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
    }
}
