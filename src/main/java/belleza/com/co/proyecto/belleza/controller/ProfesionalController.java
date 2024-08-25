package belleza.com.co.proyecto.belleza.controller;

import belleza.com.co.proyecto.belleza.core.dto.PersonalInfoDto;
import belleza.com.co.proyecto.belleza.core.dto.ProfesionalDto;
import belleza.com.co.proyecto.belleza.core.enums.EstadoRegistoProfesional;
import belleza.com.co.proyecto.belleza.core.response.ResponseHttp;
import belleza.com.co.proyecto.belleza.persistence.entity.ProfesionalEntity;
import belleza.com.co.proyecto.belleza.service.CertificadoService;
import belleza.com.co.proyecto.belleza.service.ProfesionalService;
import belleza.com.co.proyecto.belleza.service.PuntoAtencionService;
import belleza.com.co.proyecto.belleza.service.UsuarioService;
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

    private final ProfesionalService profesionalService;
    private final CertificadoService certificadoService;
    private final UsuarioService usuarioService;
    private final PuntoAtencionService puntoAtencionService;

    public ProfesionalController(ProfesionalService profesionalService, CertificadoService certificadoService, UsuarioService usuarioService, PuntoAtencionService puntoAtencionService) {
        this.profesionalService = profesionalService;
        this.certificadoService = certificadoService;
        this.usuarioService = usuarioService;
        this.puntoAtencionService = puntoAtencionService;
    }


    @PostMapping("/agregar-info-personal")
    public ResponseEntity<ResponseHttp<Boolean>> guardarCertificardo(@RequestBody PersonalInfoDto dtos) {

        try {
            this.usuarioService.updateImagenUrl(dtos.getIdProfesional(), dtos.getImage());
            this.certificadoService.guardarCertificados(dtos.getCertificadoDto());
            this.puntoAtencionService.agregarPuntoAtencion(dtos.getPuntoAtencionDto());
            this.usuarioService.actualizarEstadoRegistroProfesional(dtos.getIdProfesional(), EstadoRegistoProfesional.enPersonal);
            return ResponseHttp.successResponse("Informacion personal añadia con éxito", true);

        } catch (Exception e) {

            return ResponseHttp.errorResponse("No fue posible agregar la información personal", null);
        }
    }

}
