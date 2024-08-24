package belleza.com.co.proyecto.belleza.controller;

import belleza.com.co.proyecto.belleza.core.dto.CredencialDto;
import belleza.com.co.proyecto.belleza.core.dto.ProfesionalDto;
import belleza.com.co.proyecto.belleza.core.dto.UsuarioDto;
import belleza.com.co.proyecto.belleza.core.enums.EstadoCredencial;
import belleza.com.co.proyecto.belleza.core.enums.EstadoRegistoProfesional;
import belleza.com.co.proyecto.belleza.core.response.ResponseHttp;
import belleza.com.co.proyecto.belleza.persistence.entity.UsuarioEntity;
import belleza.com.co.proyecto.belleza.service.UsuarioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base.path}/usuario")  // Path base se obtiene desde el archivo .env
public class UsuarioController {


    @Value("${api.base.path}")
    private String basePath;

    private final UsuarioService usuarioService;
    private final CredencialController credencialController;
    private  final  ProfesionalController profesionalController;

    public UsuarioController(UsuarioService usuarioService, CredencialController credencialController, ProfesionalController profesionalController) {
        this.usuarioService = usuarioService;
        this.credencialController = credencialController;
        this.profesionalController = profesionalController;
    }

    @PostMapping("/registro")
    public ResponseEntity<ResponseHttp<UsuarioEntity>> crearUsuario(@RequestBody() UsuarioDto dto) {

        try {
            UsuarioEntity u = this.usuarioService.crearUsuario(dto);
            CredencialDto c = new CredencialDto();
            c.setContrasenia(dto.getContra());
            c.setCorreo(dto.getCorreo());
            c.setEstado(EstadoCredencial.activa);
            c.setFechaCreacion(dto.getFechaCreacion());
            c.setFechaActualizacion(dto.getFechaActualizacion());
            c.setIdUsuario(u.getIdUsuario());

            credencialController.crearCredencial(c);


            return ResponseHttp.successResponse("Usuario creado con éxito", u);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseHttp.errorResponse("No fue posible crear el usuario", null);
        }
    }


}
