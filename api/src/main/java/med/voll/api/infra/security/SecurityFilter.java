package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/*Con esta clase de filtro queremos validar que solo los ususarios autenticados
* puedan modificar o ver  los objetos de la base de datos*/
@Component// No es un servicio, repositorio o controller, el mas genérico es component
/*Para aplicaciones fuera de Spring se implementa Filter*/
public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("El filtro esta siendo llamado antes de cualquier request");
        /*El filtro debe mandar el request y la respuesta de vuelta, si no esta esto implementado
        * se retorna un 200 ok vacío.*/
        filterChain.doFilter(request,response);
    }
}
