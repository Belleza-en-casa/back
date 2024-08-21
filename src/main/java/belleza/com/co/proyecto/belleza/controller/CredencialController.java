package belleza.com.co.proyecto.belleza.controller;

import belleza.com.co.proyecto.belleza.core.dto.CredencialDto;
import belleza.com.co.proyecto.belleza.core.dto.LoginDto;
import belleza.com.co.proyecto.belleza.core.dto.Response.LoginResponse;
import belleza.com.co.proyecto.belleza.core.response.ResponseHttp;
import belleza.com.co.proyecto.belleza.service.CredencialService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base.path}/credencial")  // Path base se obtiene desde el archivo .env
public class CredencialController {

    @Value("${api.base.path}")
    private String basePath;

    private  final CredencialService credencialService;

    public CredencialController(CredencialService credencialService) {
        this.credencialService = credencialService;
    }

    public  void crearCredencial(CredencialDto dto){
        this.credencialService.guardarCredenciales(dto);
    }

    @PostMapping("/login")
    public  ResponseEntity<ResponseHttp<Boolean>> login(@RequestBody()LoginDto dto){
        try {
            return  ResponseHttp.successResponse("login exitosos", this.credencialService.login(dto.getUser(), dto.getPass()));
        }catch (Exception e){
            System.out.println(e);
            return  ResponseHttp.errorResponse("No fue posible iniciar sesion", null);
        }
    }


}
