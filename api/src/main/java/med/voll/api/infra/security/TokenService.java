package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/*En esta clase se genera un JWT token con la calve 123456 usando el
* algoritmo HMAC256*/
@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;//Configurado en el archivo properties

    public String generarToken(Usuario usuario){
        /*Parte del codigo esta en el github de auth0*/
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(usuario.getLogin())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
           throw new RuntimeException();
        }
    }

    /*
    Si el JWT es correcto devuelve el subjecct asociado a ese JWT, en este caso
            devuelve al usuario*/
    public String getSubject(String token ){
        if(token==null){
            throw new RuntimeException();
        }

        /*Parte de este codigo se obtuvo del github de Auth0: verify JWT*/
        DecodedJWT verifier=null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }

        if(verifier.getSubject()==null){
            throw new RuntimeException("verifier invalido");
        }
        return verifier.getSubject();
    }
    /*genera un tiempo de expiracion de 2 horas*/
    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }
}
