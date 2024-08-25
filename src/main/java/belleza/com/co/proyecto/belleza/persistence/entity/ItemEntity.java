package belleza.com.co.proyecto.belleza.persistence.entity;

import belleza.com.co.proyecto.belleza.core.enums.EstadoItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity()
@Setter()
@Getter()
@NoArgsConstructor()
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Integer idItem;

    private EstadoItem estado;
    @Column(unique = true)
    private String nombre;

    @ManyToOne()
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private CategoryEntity categoria;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
}
