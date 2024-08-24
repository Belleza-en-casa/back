package belleza.com.co.proyecto.belleza.persistence.repository;

import belleza.com.co.proyecto.belleza.core.enums.EstadoRegistoProfesional;
import belleza.com.co.proyecto.belleza.persistence.entity.ProfesionalEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface ProfesionalRepository extends ListCrudRepository<ProfesionalEntity, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE ProfesionalEntity p SET p.estadoRegistro = :estadoRegistro WHERE p.id = :id")
    void updateEstadoRegistroById(Integer id, EstadoRegistoProfesional estadoRegistro);
}
