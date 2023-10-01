package med.voll.api.controller;

import med.voll.api.domain.usuarios.DatosAutenticacionUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")

public class AutenticacionController {
    /*Se usa AuthenticationManager, este se configura en AuthenticationController con
    * la anotacion Bean, Por detrás lo que hace es una query a la base de datos configurada en
    * Usuario.*/
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity autenticarusuario(@RequestBody DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication token = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),datosAutenticacionUsuario.clave()); //Aqui se ingresa el usuario y contraseña que se por medio del record que creamos
        authenticationManager.authenticate(token);
        return ResponseEntity.ok().build(); // retorna un 200
    }
}
