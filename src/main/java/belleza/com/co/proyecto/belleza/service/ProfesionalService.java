package belleza.com.co.proyecto.belleza.service;

import belleza.com.co.proyecto.belleza.core.dto.ProfesionalDto;
import belleza.com.co.proyecto.belleza.persistence.entity.ProfesionalEntity;
import belleza.com.co.proyecto.belleza.persistence.repository.ProfesionalRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfesionalService {

    private final ProfesionalRepository profesionalRepository;

    public ProfesionalService(ProfesionalRepository profesionalRepository) {
        this.profesionalRepository = profesionalRepository;
    }


}
