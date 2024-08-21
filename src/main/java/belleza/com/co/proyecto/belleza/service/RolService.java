package belleza.com.co.proyecto.belleza.service;

import belleza.com.co.proyecto.belleza.core.dto.RolDto;
import belleza.com.co.proyecto.belleza.core.enums.Rol;
import belleza.com.co.proyecto.belleza.persistence.entity.RolEntity;
import belleza.com.co.proyecto.belleza.persistence.repository.RolRepository;
import org.springframework.stereotype.Service;

@Service
public class RolService {

private  final RolRepository repository;

    public RolService(RolRepository repository) {
        this.repository = repository;
    }

public  RolEntity obtenerRol(Rol rol){
        return  this.repository.findRolByNombre(rol.name());
}

    public RolEntity guardarRol(RolDto dto){

        return this.repository.save(parseRol(dto));

    }
    private  RolEntity parseRol(RolDto dto){
        RolEntity r = new RolEntity();
        r.setNombre(dto.getNombre());
        return  r;
    }



}
