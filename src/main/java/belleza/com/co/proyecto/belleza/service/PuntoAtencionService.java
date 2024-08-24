package belleza.com.co.proyecto.belleza.service;

import belleza.com.co.proyecto.belleza.core.dto.ImagenPuntoDto;
import belleza.com.co.proyecto.belleza.core.dto.PuntoAtencionDto;
import belleza.com.co.proyecto.belleza.persistence.entity.ImagenPuntoEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.ProfesionalEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.PuntoAtencionEntity;
import belleza.com.co.proyecto.belleza.persistence.repository.PuntoAtencionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PuntoAtencionService {

    private final PuntoAtencionRepository puntoAtencionRepository;

    public PuntoAtencionService(PuntoAtencionRepository puntoAtencionRepository) {
        this.puntoAtencionRepository = puntoAtencionRepository;
    }


    public void agregarPuntoAtencion(List<PuntoAtencionDto> dtos) {
        this.puntoAtencionRepository.saveAll(parseDto(dtos));
    }

    private List<PuntoAtencionEntity> parseDto(List<PuntoAtencionDto> dtos) {
        List<PuntoAtencionEntity> list = new ArrayList<>();

        for (PuntoAtencionDto i : dtos) {
            PuntoAtencionEntity p = new PuntoAtencionEntity();
            p.setDireccion(i.getDireccion());
            p.setNombre(i.getNombre());

            ProfesionalEntity pe = new ProfesionalEntity();
            pe.setIdUsuario(i.getIdProfesional());
            p.setProfesional(pe);


            List<ImagenPuntoEntity> imagenes = new ArrayList<>();
            for (ImagenPuntoDto imagenDto : i.getImagenes()) {
                ImagenPuntoEntity imagenEntity = new ImagenPuntoEntity();
                imagenEntity.setUrl(imagenDto.getUrl());
                imagenEntity.setPunto(p);
                imagenes.add(imagenEntity);
            }
            p.setImagenes(imagenes);

            list.add(p);

        }

        return list;

    }
}
