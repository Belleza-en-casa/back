package belleza.com.co.proyecto.belleza.service;

import belleza.com.co.proyecto.belleza.core.dto.CertificadoDto;
import belleza.com.co.proyecto.belleza.persistence.entity.CertificadoEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.ProfesionalEntity;
import belleza.com.co.proyecto.belleza.persistence.repository.CertificadoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;

    public CertificadoService(CertificadoRepository certificadoRepository) {
        this.certificadoRepository = certificadoRepository;
    }

    public  void guardarCertificados(List<CertificadoDto> dtos){
        this.certificadoRepository.saveAll(this.parseDto(dtos));
    }


    private List<CertificadoEntity> parseDto(List<CertificadoDto> dtos) {
        List<CertificadoEntity> list = new ArrayList<>();

        for (CertificadoDto i : dtos) {
            CertificadoEntity c = new CertificadoEntity();
            c.setInstituto(i.getInstituto());
            c.setTitulo(i.getTitulo());
            c.setAnio(i.getAnio());
            c.setFechaCreacion(i.getFechaCreacion());
            c.setFechaActualizacion(i.getFechaActualizacion());
            c.setUrl(i.getUrl());
            c.setEstado(i.getEstado());

            ProfesionalEntity p = new ProfesionalEntity();
            p.setIdUsuario(i.getIdProfesional());
            c.setProfesional(p);

            list.add(c);

        }

        return list;

    }
}
