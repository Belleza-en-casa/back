package belleza.com.co.proyecto.belleza.persistence.repository;

import belleza.com.co.proyecto.belleza.persistence.entity.UsuarioEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface UsuarioRepository extends ListCrudRepository<UsuarioEntity, Integer> {
}
