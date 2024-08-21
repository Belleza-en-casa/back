package belleza.com.co.proyecto.belleza.core.dto;

import belleza.com.co.proyecto.belleza.core.enums.EstadoRegistoProfesional;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProfesionalDto {

    private  Integer idUsuario;
    private EstadoRegistoProfesional estadoRegistro;
    private String urlDocumentoF;
    private String urlDocumentoE;
}
