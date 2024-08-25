package belleza.com.co.proyecto.belleza.service;

import belleza.com.co.proyecto.belleza.core.dto.UsuarioDto;
import belleza.com.co.proyecto.belleza.core.enums.EstadoRegistoProfesional;
import belleza.com.co.proyecto.belleza.core.enums.Rol;
import belleza.com.co.proyecto.belleza.persistence.entity.AdminEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.ProfesionalEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.RolEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.UsuarioEntity;
import belleza.com.co.proyecto.belleza.persistence.repository.ProfesionalRepository;
import belleza.com.co.proyecto.belleza.persistence.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolService rolService;
    private final ProfesionalRepository profesionalRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RolService rolService, ProfesionalRepository profesionalRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolService = rolService;
        this.profesionalRepository = profesionalRepository;
    }

    public UsuarioEntity crearUsuario(UsuarioDto dto) {
        return this.usuarioRepository.save(parseDto(dto));
    }


    public UsuarioEntity obtenerUsuario(String correo) {

        Optional<UsuarioEntity> user = this.usuarioRepository.findByCorreo(correo);
        return user.orElse(null);
    }

    public void actualizarEstadoRegistroProfesional(Integer id, EstadoRegistoProfesional nuevoEstado) {
        profesionalRepository.updateEstadoRegistroById(id, nuevoEstado);
    }

    public void updateImagenUrl(Integer id, String url) {
        usuarioRepository.updateUrlImagenById(id, url);
    }

    private UsuarioEntity parseDto(UsuarioDto dto) {


        UsuarioEntity u = new UsuarioEntity();

        if (dto.getRol() == Rol.profesional) {
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
            RolEntity re = new RolEntity();
            re.setIdRol(r.getIdRol());
            p.setRol(re);
            p.setUrlDocumentoE(dto.getUrlDocumentoE());
            p.setUrlDocumentoF(dto.getUrlDocumentoF());
            p.setEstadoRegistro(EstadoRegistoProfesional.noIniciada);

            return p;

        }
        if (dto.getRol() == Rol.cliente) {

        }
        System.out.println(dto.getRol() +"--->");
        if (dto.getRol() == Rol.admin) {
            AdminEntity a = new AdminEntity();

            a.setNombres(dto.getNombres());
            a.setApellidos(dto.getApellidos());
            a.setCorreo(dto.getCorreo());
            a.setFechaNacimiento(dto.getFechaNacimiento());
            a.setEstado(dto.getEstado());
            a.setIdentificacion(dto.getIdentificacion());

            a.setFechaCreacion(dto.getFechaCreacion());
            a.setFechaActualizacion(dto.getFechaActualizacion());

            RolEntity r = this.rolService.obtenerRol(dto.getRol());
            RolEntity re = new RolEntity();
            re.setIdRol(r.getIdRol());
            a.setRol(re);
            return  a;
        }

        return u;


    }

}
