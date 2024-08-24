package belleza.com.co.proyecto.belleza.core.dto;

import belleza.com.co.proyecto.belleza.core.enums.EstadoCertificado;
import belleza.com.co.proyecto.belleza.persistence.entity.ProfesionalEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CertificadoDto {


    private String titulo;

    private String instituto;

    private String anio;

    private String url;

    private EstadoCertificado estado;

    private Integer idProfesional;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
}
