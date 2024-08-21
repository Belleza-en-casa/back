package belleza.com.co.proyecto.belleza.controller;

import belleza.com.co.proyecto.belleza.core.dto.ProfesionalDto;
import belleza.com.co.proyecto.belleza.core.response.ResponseHttp;
import belleza.com.co.proyecto.belleza.persistence.entity.ProfesionalEntity;
import belleza.com.co.proyecto.belleza.service.ProfesionalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base.path}/profesional")  // Path base se obtiene desde el archivo .env

public class ProfesionalController {

    @Value("${api.base.path}")
    private String basePath;

    private  final ProfesionalService profesionalService;

    public ProfesionalController(ProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }


}
