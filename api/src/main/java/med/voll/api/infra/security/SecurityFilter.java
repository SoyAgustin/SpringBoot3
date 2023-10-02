package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/*Con esta clase de filtro queremos validar que solo los ususarios autenticados
* puedan modificar o ver  los objetos de la base de datos*/
@Component// No es un servicio, repositorio o controller, el mas genérico es component
/*Para aplicaciones fuera de Spring se implementa Filter*/
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*Se debe obtener el token por medio del header
        * Se debe usar Authorization, es el estándar.
        * En este caso se carga el token en Insomnia en la pestaña de bearer*/

        var token =request.getHeader("Authorization");

        if(token !=null ){
            System.out.println("validamos que el token no es null");
            token = token.replace("Bearer ","");
            System.out.println(token);
            System.out.println(tokenService.getSubject(token));
        }else{
            throw new RuntimeException("Token vacio");
        }
        filterChain.doFilter(request,response);

    }
}
