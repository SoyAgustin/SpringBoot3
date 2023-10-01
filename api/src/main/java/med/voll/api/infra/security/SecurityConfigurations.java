package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
        return httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().build();
    }
}
