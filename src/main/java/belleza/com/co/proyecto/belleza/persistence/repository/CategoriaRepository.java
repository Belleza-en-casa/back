package belleza.com.co.proyecto.belleza.persistence.repository;

import belleza.com.co.proyecto.belleza.core.enums.EstadoCategoria;
import belleza.com.co.proyecto.belleza.persistence.entity.CategoryEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CategoriaRepository extends ListCrudRepository<CategoryEntity, Integer> {


    List<CategoryEntity> findAllByEstado(EstadoCategoria estadoCategoria);
}
