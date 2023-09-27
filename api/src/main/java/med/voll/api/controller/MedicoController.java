package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;
    @PostMapping
    /*Los generics de ResponnseEntity son el tipo de respuesta del método*/
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder){

       Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));

       DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(
               medico.getId(),
               medico.getNombre(),
               medico.getEmail(),
               medico.getTelefono(),
               medico.getEspecialidad().toString(),
               new DatosDireccion(
                       medico.getDireccion().getCalle(),
                       medico.getDireccion().getDistrito(),
                       medico.getDireccion().getCiudad(),
                       medico.getDireccion().getNumero(),
                       medico.getDireccion().getComplemento()
            )
       );
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
       return ResponseEntity.created(url).body(datosRespuestaMedico);
    //Se debe devolver un 201 Created
    // De forma estándar (no solo java) se debe retornar la ruta en donde se guardo el objeto.
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
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size= 2)  Pageable paginacion){
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        /*Se pueden hacer consultas dinámicas con findBy"Nombre de la columna""Valor"*/
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }


    @PutMapping
    @Transactional
    /*Ahora se retornna un 200 ok pero tambien se retornnan los datos actualizados*/
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
    Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
    medico.actualizarDatos(datosActualizarMedico);
    return ResponseEntity.ok(new DatosRespuestaMedico(
            medico.getId(),
            medico.getNombre(),
            medico.getEmail(),
            medico.getTelefono(),
            medico.getEspecialidad().toString(),
            new DatosDireccion(
                    medico.getDireccion().getCalle(),
                    medico.getDireccion().getDistrito(),
                    medico.getDireccion().getCiudad(),
                    medico.getDireccion().getNumero(),
                    medico.getDireccion().getComplemento()
            )
        )
    );
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
    /*Usando ResponseEntity podemos retornar el código html correcto
    * en este caso un no Content que corresponde a un 204*/
    public ResponseEntity minarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornnarDatosMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico =new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(
                        medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()
                )
        );
        return ResponseEntity.ok(datosMedico);
    }

}
