package belleza.com.co.proyecto.belleza.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rol")
@Setter
@Getter
@NoArgsConstructor
public class RolEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_rol")
private  Integer idRol;


private  String nombre;

    @OneToMany(mappedBy = "rol",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<UsuarioEntity> usuarios;
}
