package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    /*En el metodo SecurityFilterChain se cambia la configuracion de autenticación por defecto
    * que es la connfiguracion web csrf, como estamos a nivel de API y no a nnivel de aplicacion web
    * se deshabilita esta forma de autenticar y se utiliza Stateless.*/
    /*De esta forma es como si o hubieramos hecho nada en términos de seguridad porque ya lo
    * teníamos sin autenticacion por defecto.*/
    @Bean//Anotacion importante para que Spring detecte el método y se cambie la configuracion
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }

    /*Se usa e AuthenticationcController, realiza una consulta a la base de datos configurada,
    * en este caso Ususario y busca el usuario y conntraseña*/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*Con este método le estamos diciendo que el algoritmo de encriptacion es BCrypt, el usuario ingresa la contraseña normal
    * y Spring se encarga de comparar con el valor encriptado. En la db se tiene que tner el valor encriptado de la clave del usr*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
