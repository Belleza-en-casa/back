package belleza.com.co.proyecto.belleza.core.dto;


import lombok.Data;

import java.util.List;

@Data

public class PersonalInfoDto {

    private String image;
    private  Integer idProfesional;
    private List<CertificadoDto> certificadoDto;
    private  List<PuntoAtencionDto> puntoAtencionDto;
}
