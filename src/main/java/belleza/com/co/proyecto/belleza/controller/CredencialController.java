package belleza.com.co.proyecto.belleza.controller;

import belleza.com.co.proyecto.belleza.core.dto.CredencialDto;
import belleza.com.co.proyecto.belleza.core.dto.LoginDto;
import belleza.com.co.proyecto.belleza.core.dto.Response.LoginResponse;
import belleza.com.co.proyecto.belleza.core.response.ResponseHttp;
import belleza.com.co.proyecto.belleza.core.util.JwtUtil;
import belleza.com.co.proyecto.belleza.persistence.entity.ProfesionalEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.UsuarioEntity;
import belleza.com.co.proyecto.belleza.service.CredencialService;
import belleza.com.co.proyecto.belleza.service.UsuarioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${api.base.path}/credencial")  // Path base se obtiene desde el archivo .env
public class CredencialController {

    @Value("${api.base.path}")
    private String basePath;

    private final JwtUtil jwtUtil;

    private final CredencialService credencialService;
    private final UsuarioService usuarioService;

    public CredencialController(JwtUtil jwtUtil, CredencialService credencialService, UsuarioService usuarioService) {
        this.jwtUtil = jwtUtil;
        this.credencialService = credencialService;
        this.usuarioService = usuarioService;
    }

    public void crearCredencial(CredencialDto dto) {
        this.credencialService.guardarCredenciales(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseHttp< Map<String, Object>>> login(@RequestBody() LoginDto dto) {
        try {
            boolean login = this.credencialService.login(dto.getUser(), dto.getPass());
            Map<String, Object> map = new HashMap<>();

            if (login) {
                UsuarioEntity u = this.usuarioService.obtenerUsuario(dto.getUser());
                map = this.buildLoginResponse(u);
            }
            return ResponseHttp.successResponse("login exitosos", map );
        } catch (Exception e) {
            System.out.println(e);
            return ResponseHttp.errorResponse("No fue posible iniciar sesion", null);
        }
    }


    private Map<String, Object> buildLoginResponse(UsuarioEntity u) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        ProfesionalEntity p = this.usuarioService.obtenerProfesinal(u.getIdUsuario());
        map2.put("rol", u.getRol().getNombre());
        map2.put("userId", u.getIdUsuario());
        map2.put("nombre", u.getNombres());
        map.put("token", this.jwtUtil.create(u.getCorreo(), map2));

        map.put("rol", u.getRol().getNombre());
        map.put("userId", u.getIdUsuario());
        map.put("nombres", u.getNombres());
        map.put("apellidos", u.getApellidos());
        map.put("identificacion", u.getIdentificacion());
        map.put("estado", u.getEstado());
        map.put("estadoRegistro", p.getEstadoRegistro());




        return map;
    }
}
