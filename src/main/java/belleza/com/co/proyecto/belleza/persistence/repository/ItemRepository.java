package belleza.com.co.proyecto.belleza.persistence.repository;

import belleza.com.co.proyecto.belleza.core.enums.EstadoItem;
import belleza.com.co.proyecto.belleza.persistence.entity.ItemEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ItemRepository extends ListCrudRepository<ItemEntity, Integer> {


    List<ItemEntity> findAllByEstado(EstadoItem estadoItem);
}
