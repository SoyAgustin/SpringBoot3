package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*En esta clase se van a tratar los errores a nivel de controller, no en cada método*/
/*Spring AOP: Programacion orientada a aspectos*/
@RestControllerAdvice
public class TratadorDeErrores {

    /*Cuando se identidique la exception EntityNot FoundException se va a lanzar
    * ese método. En cualquier parte del código que se encuentre esa excepción se
    * va a lanzar este método que retorna un 404 error*/
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return  ResponseEntity.notFound().build(); // Este es el 404 error sin mensaje de regreso
    }

    /*Con este método vamos a solucionar el código de error que se envía
    * en el payload de refgreso al cliente, para que solo muestre algunos y no todos los
    * errores a nivel de clases.*/
    /*Por ejemplo cuando se registra un médico sin los campos de nombre, email y documento*/
    /*El argumento del metodo debe ser el mismo que el tipo de excepcion que declaramos
    * en el ExceptionHandler*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return  ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity errorHandlerValidacionesDeNegocio(Exception e){
        return  ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidationException(Exception e){
        return  ResponseEntity.badRequest().body(e.getMessage());
    }

    /*Este es el DTO para los mensajes del error 400*/
    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage()); //solo devolvemos el campo y el mensaje
        }
    }

}
