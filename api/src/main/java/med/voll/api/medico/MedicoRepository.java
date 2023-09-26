package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;

/*Con un repositrio se puede hacer la gestion con la DB
* sustiruye al patrón DAO
* La interface ya tiene metodos de guardado, eliminar, encontrar por Id, etc.*/
public interface MedicoRepository extends JpaRepository<Medico,Long> {
}
