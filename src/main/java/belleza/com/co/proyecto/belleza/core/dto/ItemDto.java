package belleza.com.co.proyecto.belleza.core.dto;

import belleza.com.co.proyecto.belleza.core.enums.EstadoItem;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemDto {


    private EstadoItem estado;

    private String nombre;

    private Integer idCategoria;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
}
