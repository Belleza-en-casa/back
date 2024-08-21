package belleza.com.co.proyecto.belleza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "horario")
public class HorarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Integer idHorario;

    private String dia;

    private String horaAmI;

    private String horaAmF;

    private String horaPmI;

    private String horaPmf;

    @ManyToOne()
    @JoinColumn(name = "id_profesional", referencedColumnName = "id_profesional")
    private ProfesionalEntity profesional;

    private LocalDateTime fecha_creacion;

    private LocalDateTime fecha_actualizacion;
}
