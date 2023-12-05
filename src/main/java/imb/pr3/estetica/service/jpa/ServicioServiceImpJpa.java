package imb.pr3.estetica.service.jpa;

import imb.pr3.estetica.entity.Servicio;
import imb.pr3.estetica.repository.ServicioRepository;
import imb.pr3.estetica.service.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpJpa implements IServicioService {
    @Autowired
    private ServicioRepository servicioRepository;


    @Override
    public List<Servicio> buscarServicio() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio buscarServicioPorId(Integer id) {
        Optional<Servicio> optional = servicioRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio eliminarServicio(Integer id) {
        Optional<Servicio> optional = servicioRepository.findById(id);
        Servicio servicio = null;
        if (optional.isPresent()) {
            // Extrae el objeto Servicio del Optional y luego elimínalo
            servicio = optional.get();
            servicioRepository.delete(servicio);
        }
        return servicio;
    }

}