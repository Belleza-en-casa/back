package belleza.com.co.proyecto.belleza.persistence.repository;

import belleza.com.co.proyecto.belleza.persistence.entity.CredencialEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface CredencialRepository extends ListCrudRepository<CredencialEntity, Integer> {

    Optional<CredencialEntity> findByCorreo(String correo);
}
