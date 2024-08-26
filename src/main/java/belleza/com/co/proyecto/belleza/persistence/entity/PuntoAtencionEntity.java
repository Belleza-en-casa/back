package belleza.com.co.proyecto.belleza.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "punto_atencion")
@Setter
@Getter
@NoArgsConstructor
public class PuntoAtencionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_punto_atencion")
    private Integer idPuntoAtencion;

    private String nombre;

    private String direccion;

    @ManyToOne()
    @JoinColumn(name = "id_profesional", referencedColumnName = "id_profesional")
    private ProfesionalEntity profesional;


    @OneToMany(mappedBy = "punto", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImagenPuntoEntity> imagenes = new ArrayList<>();


}
