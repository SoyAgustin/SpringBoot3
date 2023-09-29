package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/*Con un repositrio se puede hacer la gestion con la DB
* sustiruye al patrón DAO
* La interface ya tiene metodos de guardado, eliminar, encontrar por Id, etc.*/
public interface MedicoRepository extends JpaRepository<Medico,Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);
}