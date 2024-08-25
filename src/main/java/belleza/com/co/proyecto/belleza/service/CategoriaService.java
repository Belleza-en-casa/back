package belleza.com.co.proyecto.belleza.service;

import belleza.com.co.proyecto.belleza.core.dto.CategoriaDto;
import belleza.com.co.proyecto.belleza.core.dto.ItemDto;
import belleza.com.co.proyecto.belleza.core.enums.EstadoCategoria;
import belleza.com.co.proyecto.belleza.core.enums.EstadoItem;
import belleza.com.co.proyecto.belleza.persistence.entity.AdminEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.CategoryEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.ItemEntity;
import belleza.com.co.proyecto.belleza.persistence.repository.CategoriaRepository;
import belleza.com.co.proyecto.belleza.persistence.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private  final ItemRepository itemRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, ItemRepository itemRepository) {
        this.categoriaRepository = categoriaRepository;
        this.itemRepository = itemRepository;
    }

    public CategoryEntity createCategory(CategoriaDto categoriaDto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setNombre(categoriaDto.getNombre());
        categoryEntity.setEstado(categoriaDto.getEstado());
        categoryEntity.setFechaCreacion(categoriaDto.getFechaCreacion());
        categoryEntity.setFechaActualizacion(categoriaDto.getFechaActualizacion());

        AdminEntity admin = new AdminEntity();
        admin.setIdUsuario(categoriaDto.getIdAdmin());
        categoryEntity.setAdmin(admin);

        for (ItemDto itemDto : categoriaDto.getItems()) {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setNombre(itemDto.getNombre());
            itemEntity.setEstado(itemDto.getEstado());
            itemEntity.setFechaCreacion(itemDto.getFechaCreacion());
            itemEntity.setFechaActualizacion(itemDto.getFechaActualizacion());
            itemEntity.setCategoria(categoryEntity);
            categoryEntity.getItems().add(itemEntity);
        }

        return categoriaRepository.save(categoryEntity);
    }

    public List<CategoryEntity> obtenerCategorias(EstadoCategoria estadoCategoria) {

        return this.categoriaRepository.findAllByEstado(estadoCategoria);
    }
    public List<ItemEntity> obtenerItems(EstadoItem estado) {

        return this.itemRepository.findAllByEstado(estado);
    }


}
