package belleza.com.co.proyecto.belleza.persistence.repository;

import belleza.com.co.proyecto.belleza.core.enums.EstadoRegistoProfesional;
import belleza.com.co.proyecto.belleza.persistence.entity.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends ListCrudRepository<UsuarioEntity, Integer> {


    Optional<UsuarioEntity> findByCorreo(String correo);
    @Transactional
    @Modifying
    @Query("UPDATE UsuarioEntity p SET p.urlImagen = :urlImagen WHERE p.idUsuario = :id")
    void updateUrlImagenById(Integer id, String urlImagen);

}
