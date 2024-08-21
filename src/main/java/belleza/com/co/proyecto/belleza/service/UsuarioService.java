package belleza.com.co.proyecto.belleza.service;

import belleza.com.co.proyecto.belleza.core.dto.UsuarioDto;
import belleza.com.co.proyecto.belleza.core.enums.EstadoRegistoProfesional;
import belleza.com.co.proyecto.belleza.core.enums.Rol;
import belleza.com.co.proyecto.belleza.persistence.entity.ProfesionalEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.RolEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.UsuarioEntity;
import belleza.com.co.proyecto.belleza.persistence.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

private  final UsuarioRepository usuarioRepository;
private  final  RolService rolService;
    public UsuarioService(UsuarioRepository usuarioRepository, RolService rolService) {
        this.usuarioRepository = usuarioRepository;
        this.rolService = rolService;
    }

    public  UsuarioEntity crearUsuario(UsuarioDto dto){
        return  this.usuarioRepository.save(parseDto(dto));
    }

    private UsuarioEntity parseDto(UsuarioDto dto){


        UsuarioEntity u = new UsuarioEntity();

        if(dto.getRol()== Rol.profesional){
            ProfesionalEntity p = new ProfesionalEntity();


            p.setNombres(dto.getNombres());
            p.setApellidos(dto.getApellidos());
            p.setCorreo(dto.getCorreo());
            p.setFechaNacimiento(dto.getFechaNacimiento());
            p.setEstado(dto.getEstado());
            p.setIdentificacion(dto.getIdentificacion());

            p.setFechaCreacion(dto.getFechaCreacion());
            p.setFechaActualizacion(dto.getFechaActualizacion());

            RolEntity r = this.rolService.obtenerRol(dto.getRol());
            RolEntity re  = new RolEntity();
            re.setIdRol(r.getIdRol());
            p.setRol(re);
            p.setUrlDocumentoE(dto.getUrlDocumentoE());
            p.setUrlDocumentoF(dto.getUrlDocumentoF());
            p.setEstadoRegistro(EstadoRegistoProfesional.noIniciada);

            return  p;

        }
        if(dto.getRol()== Rol.cliente){

        }

        return  u;


    }

}
