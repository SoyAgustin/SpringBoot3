package med.voll.api.infra.security;

/*DTO para retornar el JWT cuando se hace el POST de usuario y contraseña
* con el fin de seguir con el estándar en la aplicacioon de cconsumir DTO y regresar DTO*/
public record DatosJWTToken (String JWTtoken){
}
