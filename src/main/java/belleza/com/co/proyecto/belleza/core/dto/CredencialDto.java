package belleza.com.co.proyecto.belleza.core.dto;

import belleza.com.co.proyecto.belleza.core.enums.EstadoCredencial;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CredencialDto {

    private String correo;

    private String contrasenia;

    private EstadoCredencial estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    private Integer idUsuario;
}
