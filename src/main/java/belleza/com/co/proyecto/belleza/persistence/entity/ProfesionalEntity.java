package belleza.com.co.proyecto.belleza.persistence.entity;

import belleza.com.co.proyecto.belleza.core.enums.EstadoRegistoProfesional;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "profesional")
@PrimaryKeyJoinColumn(name = "id_profesional", referencedColumnName = "id_usuario")

public class ProfesionalEntity extends UsuarioEntity {


    private EstadoRegistoProfesional estadoRegistro;

    @Column(name = "url_frontal")
    private String urlDocumentoF;

    @Column(name = "url_posterior")
    private String urlDocumentoE;

    @OneToMany(mappedBy = "profesional", fetch =FetchType.EAGER )
    @JsonIgnore
    private List<CertificadoEntity> certificados;

    @OneToMany(mappedBy = "profesional", fetch =FetchType.EAGER )
    @JsonIgnore
    private List<PuntoAtencionEntity> puntos;

    @OneToMany(mappedBy = "profesional", fetch =FetchType.EAGER )
    @JsonIgnore
    private List<HorarioEntity> horario;
}
