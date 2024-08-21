package belleza.com.co.proyecto.belleza.persistence.entity;

import belleza.com.co.proyecto.belleza.core.enums.EstadoCertificado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "certificado")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class CertificadoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_certificado")
    private Integer idCertificado;

    private String titulo;

    private String instituto;

    private String anio;

    private String url;

    private EstadoCertificado estado;

    @ManyToOne()
    @JoinColumn(name = "id_profesional", referencedColumnName = "id_profesional")
    private ProfesionalEntity profesional;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
}
