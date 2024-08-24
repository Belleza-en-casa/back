package belleza.com.co.proyecto.belleza.persistence.entity;

import belleza.com.co.proyecto.belleza.core.enums.EstadoUsuario;
import belleza.com.co.proyecto.belleza.core.enums.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public class UsuarioEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String nombres;

    private String apellidos;

    private String urlImagen;

    private String identificacion;

    private String correo;

    private LocalDateTime fechaNacimiento;

    private EstadoUsuario estado;

    @ManyToOne()
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private RolEntity rol;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
}
