package belleza.com.co.proyecto.belleza.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "admin")
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_admin", referencedColumnName = "id_usuario")

public class AdminEntity extends  UsuarioEntity {


    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<CategoryEntity> categorias;
}
