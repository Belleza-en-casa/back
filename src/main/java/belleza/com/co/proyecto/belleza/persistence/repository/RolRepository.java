package belleza.com.co.proyecto.belleza.persistence.repository;


import belleza.com.co.proyecto.belleza.core.enums.Rol;
import belleza.com.co.proyecto.belleza.persistence.entity.RolEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface RolRepository extends ListCrudRepository<RolEntity, Integer> {


    RolEntity findRolByNombre(String nombre);
}
