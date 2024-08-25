package belleza.com.co.proyecto.belleza.service;

import belleza.com.co.proyecto.belleza.core.dto.CredencialDto;
import belleza.com.co.proyecto.belleza.core.enums.Rol;
import belleza.com.co.proyecto.belleza.persistence.entity.CredencialEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.RolEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.UsuarioEntity;
import belleza.com.co.proyecto.belleza.persistence.repository.CredencialRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CredencialService implements UserDetailsService {

    private  final CredencialRepository credencialRepository;
    private  final BCryptPasswordEncoder passwordEncoder;
    private  final  RolService rolService;

    public CredencialService(CredencialRepository credencialRepository, BCryptPasswordEncoder passwordEncoder, RolService rolService) {
        this.credencialRepository = credencialRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolService = rolService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CredencialEntity credencial = credencialRepository.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Rol r = Rol.valueOf(credencial.getUsuario().getRol().getNombre());
        System.out.println("---->_->->"+r);
        return new org.springframework.security.core.userdetails.User(
                credencial.getCorreo(),
                credencial.getContrasenia(),

                this.grantedAuthorities(r)
        );
    }

    public boolean login(String usuario, String contrasenia) {
        Optional<CredencialEntity> user = credencialRepository.findByCorreo(usuario);
        Boolean b=  passwordEncoder.matches(contrasenia, user.get().getContrasenia());
        return user.filter(credencialEntity -> passwordEncoder.matches(contrasenia, credencialEntity.getContrasenia())).isPresent();
    }

    public void guardarCredenciales(CredencialDto credencialDto){
            this.credencialRepository.save(parseCredencial(credencialDto));
    }


  ///parseCredencial convierte el dto que llega en la entidad
    private CredencialEntity parseCredencial(CredencialDto dto){
        CredencialEntity c = new CredencialEntity();

        c.setCorreo(dto.getCorreo());
        c.setContrasenia(dto.getContrasenia());
        c.setFechaActualizacion(dto.getFechaActualizacion());
        c.setFechaCreacion(dto.getFechaCreacion());
        c.setEstado(dto.getEstado());

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setIdUsuario(dto.getIdUsuario());
        c.setUsuario(usuario);
        return  c;
    }

    private String[] getAuthorities(Rol role) {
        List<RolEntity> roles = this.rolService.obtenerRoles();
        for (RolEntity r : roles) {
            if (r.getNombre().equals(role.name())) {
                RolEntity rol = rolService.obtenerRol(role);
                r.setNombre(role.name());

                List<String> auth = new ArrayList<>();
                return auth.toArray(new String[0]);
            }

        }
        return new String[]{};
    }

    private List<GrantedAuthority> grantedAuthorities(Rol role) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

        for (String authority : this.getAuthorities(role)) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }

        return authorities;
    }
}

