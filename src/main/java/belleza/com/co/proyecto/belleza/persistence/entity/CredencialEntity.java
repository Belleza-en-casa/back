package belleza.com.co.proyecto.belleza.persistence.entity;

import belleza.com.co.proyecto.belleza.core.enums.EstadoCredencial;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "credencial")
@Setter
@Getter
@NoArgsConstructor
public class CredencialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credencial")

    private Integer idCredencial;

    private String correo;

    private String contrasenia;

    private EstadoCredencial estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    @OneToOne()
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private  UsuarioEntity usuario;
}
