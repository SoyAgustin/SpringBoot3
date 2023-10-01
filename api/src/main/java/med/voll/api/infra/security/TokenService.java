package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

/*En esta clase se genera un JWT token con la calve 123456 usando el
* algoritmo HMAC256*/
@Service
public class TokenService {

    public String generarToken(){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject("agustin.djp")
                    .sign(algorithm);
        } catch (JWTCreationException exception){
           throw new RuntimeException();
        }
    }
}
