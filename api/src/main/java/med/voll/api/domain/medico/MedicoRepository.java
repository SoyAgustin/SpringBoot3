package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/*Con un repositrio se puede hacer la gestion con la DB
* sustiruye al patrón DAO
* La interface ya tiene metodos de guardado, eliminar, encontrar por Id, etc.*/
@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);

    /*Con esta anotacion se pueden hacer querys de JPA
    * la sintaxis es abrir y cerrar con tres comillas*/
    @Query("""
            SELECT m FROM Medico m 
            WHERE m.activo = TRUE AND
            m.especialidad=:especialidad AND
            m.id NOT IN(
                SELECT c.medico.id FROM Consulta c
                WHERE c.data=:fecha
            )
            ORDER BY RAND()
            LIMIT 1
            """)
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);

    Boolean findActivoById(Long idMedico);
}
