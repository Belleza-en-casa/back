package belleza.com.co.proyecto.belleza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "imagen_punto")
@Setter
@Getter
@NoArgsConstructor
public class ImagenPuntoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen_punto")
    private  Integer idImagenPunto;

    private String url;

    @ManyToOne()
    @JoinColumn(name = "id_punto_atencion", referencedColumnName = "id_punto_atencion")
    private PuntoAtencionEntity punto;




}
