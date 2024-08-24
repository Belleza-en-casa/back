package belleza.com.co.proyecto.belleza.core.dto;


import lombok.Data;

import java.util.List;
@Data
public class PuntoAtencionDto {



    private String nombre;
    private String direccion;
    private Integer idProfesional;
    private List<ImagenPuntoDto> imagenes;
}
