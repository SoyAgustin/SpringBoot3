package med.voll.api.infra.springdoc;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Esta clase sirve para configurar la pagina de swagger de la documentacion
* Segun la documentacion se debe agregar una anotacion @SecurityRequirement(name = "bearer-key")
* en cada uno de los controllers que necesiten usar el JWT
*
* En este caso son las consultas, medicos y pacientes*/
@Configuration
public class SpringDocConfiguration {

    /*Con este metodo se pueden agregar los encabezdos a los métodos para ingresar un JWT y poder
    * testear los endpoints en la página*/
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
