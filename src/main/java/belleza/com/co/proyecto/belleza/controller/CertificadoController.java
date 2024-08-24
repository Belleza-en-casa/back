package belleza.com.co.proyecto.belleza.controller;

import belleza.com.co.proyecto.belleza.core.dto.CertificadoDto;
import belleza.com.co.proyecto.belleza.core.dto.PersonalInfoDto;
import belleza.com.co.proyecto.belleza.core.enums.EstadoRegistoProfesional;
import belleza.com.co.proyecto.belleza.core.response.ResponseHttp;
import belleza.com.co.proyecto.belleza.service.CertificadoService;
import belleza.com.co.proyecto.belleza.service.PuntoAtencionService;
import belleza.com.co.proyecto.belleza.service.UsuarioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("${api.base.path}/certificado")  // Path base se obtiene desde el archivo .env

public class CertificadoController {
    @Value("${api.base.path}")
    private String basePath;

    private final CertificadoService certificadoService;
    private final UsuarioService usuarioService;
    private  final PuntoAtencionService puntoAtencionService;

    public CertificadoController(CertificadoService certificadoService, UsuarioService usuarioService, PuntoAtencionService puntoAtencionService) {
        this.certificadoService = certificadoService;
        this.usuarioService = usuarioService;
        this.puntoAtencionService = puntoAtencionService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<ResponseHttp<Boolean>> guardarCertificardo(@RequestBody PersonalInfoDto dtos) {

        try {
            this.certificadoService.guardarCertificados(dtos.getCertificadoDto());
            this.usuarioService.actualizarEstadoRegistroProfesional(dtos.getIdProfesional(), EstadoRegistoProfesional.enPersonal);
            this.usuarioService.updateImagenUrl(dtos.getIdProfesional(), dtos.getImage());
            this.puntoAtencionService.agregarPuntoAtencion(dtos.getPuntoAtencionDto());
            return ResponseHttp.successResponse("Certificados añadidos con éxito", true);

        } catch (Exception e) {

            return ResponseHttp.errorResponse("No fue posible agregar el certificado", null);
        }
    }
}
