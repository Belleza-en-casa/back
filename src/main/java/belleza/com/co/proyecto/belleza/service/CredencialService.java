package belleza.com.co.proyecto.belleza.service;

import belleza.com.co.proyecto.belleza.core.dto.CredencialDto;
import belleza.com.co.proyecto.belleza.persistence.entity.CredencialEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.UsuarioEntity;
import belleza.com.co.proyecto.belleza.persistence.repository.CredencialRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CredencialService implements UserDetailsService {

    private  final CredencialRepository credencialRepository;
    private  final BCryptPasswordEncoder passwordEncoder;

    public CredencialService(CredencialRepository credencialRepository, BCryptPasswordEncoder passwordEncoder) {
        this.credencialRepository = credencialRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CredencialEntity credencial = credencialRepository.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                credencial.getCorreo(),
                credencial.getContrasenia(),
                new ArrayList<>() // Authority list
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
}
