package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.DatosAgendarConsulta;

/*Como en todos los validadores la firma del metodo validar es la misma
* creamos una interface para que se utilice el mismo patrón en otros
* validadodres en el futuro*/
public interface ValidadorDeConsultas {
    public void validar(DatosAgendarConsulta datos);
}
