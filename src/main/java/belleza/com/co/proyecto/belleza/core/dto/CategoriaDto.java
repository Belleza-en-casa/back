package belleza.com.co.proyecto.belleza.core.dto;

import belleza.com.co.proyecto.belleza.core.enums.EstadoCategoria;
import belleza.com.co.proyecto.belleza.persistence.entity.AdminEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.ItemEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoriaDto {


    private String nombre;

    private EstadoCategoria estado;

    private Integer idAdmin;

    private LocalDateTime fechaCreacion;

    private LocalDateTime FechaActualizacion;

    private List<ItemDto> items;
}

