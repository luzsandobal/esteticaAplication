package imb.pr3.estetica.service.jpa;

import imb.pr3.estetica.entity.MetodoDePago;
import imb.pr3.estetica.repository.MetodoDePagoRepository;
import imb.pr3.estetica.service.IMetodoDePagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetodoDePagoServiceImpJPA implements IMetodoDePagoService {
    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;

    @Override
    public List<MetodoDePago> buscarTodos() {
        // Este método busca y devuelve una lista de todos los métodos de pago en la base de datos.
        List<MetodoDePago> entities = metodoDePagoRepository.findAll();
        return entities;
    }

    @Override
    public MetodoDePago buscarPorId(Integer id) {
        // Este método busca un método de pago por su ID y lo devuelve si se encuentra.

        //  Optional es una clase introducida en Java para manejar valores que pueden
        //  ser nulos o no nulos de una manera más segura y explícita.
        Optional<MetodoDePago> entityOptional = metodoDePagoRepository.findById(id);

        // Si realmente existe, esta llamada a get() devuelve la entidad MetodoDePago.
        return entityOptional.get();
    }

    @Override
    public MetodoDePago guardar(MetodoDePago entity) {
        // Este método guarda un nuevo método de pago en la base de datos y devuelve la entidad guardada.
        return metodoDePagoRepository.save(entity);
    }

    @Override
    public boolean eliminar(Integer id) {
        // Este método intenta eliminar un método de pago por su ID.

        if (metodoDePagoRepository.existsById(id)) {// Si el metodo de pago existe.
            metodoDePagoRepository.deleteById(id); // El método de pago se elimina.
            return true;// Devuelve true si el método de pago se eliminó con éxito.
        } else {
            return false;// Devuelve false si el método de pago no existe.
        }
    }

    @Override
    public boolean existe(Integer id) {
        // Este método verifica si un método de pago con el ID especificado existe en la base de datos.
        return (id == null) ? false : metodoDePagoRepository.existsById(id);
    }
}