package belleza.com.co.proyecto.belleza.core.dto;

import belleza.com.co.proyecto.belleza.core.enums.EstadoRegistoProfesional;
import belleza.com.co.proyecto.belleza.core.enums.EstadoUsuario;
import belleza.com.co.proyecto.belleza.core.enums.Rol;
import belleza.com.co.proyecto.belleza.persistence.entity.RolEntity;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioDto {


    private String nombres;

    private String apellidos;

    private String identificacion;

    private String correo;

    private String contra;

    private LocalDateTime fechaNacimiento;

    private EstadoUsuario estado;

    private Rol rol;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    private String urlDocumentoF;

    private String urlDocumentoE;

    private EstadoRegistoProfesional estadoRegistro;

}
