package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
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
        return  ResponseEntity.notFound().build(); // Este es el 404 error
    }
}
