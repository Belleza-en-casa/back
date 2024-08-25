package belleza.com.co.proyecto.belleza.persistence.entity;

import belleza.com.co.proyecto.belleza.core.enums.EstadoCategoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(unique = true)
    private String nombre;

    private EstadoCategoria estado;

    @ManyToOne()
    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin")
    @JsonIgnore
    private AdminEntity admin;

    private LocalDateTime fechaCreacion;

    private LocalDateTime FechaActualizacion;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ItemEntity> items = new ArrayList<>();
}
