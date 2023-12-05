package imb.pr3.estetica.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import imb.pr3.estetica.entity.Cliente;
import imb.pr3.estetica.repository.ClienteRepository;
import imb.pr3.estetica.service.IClienteService;

@Service
@Primary
public class ClienteServiceImpJpa implements IClienteService {
	
	@Autowired
	ClienteRepository repo;
		
	@Override
	public List<Cliente> buscarTodos() {
		return repo.findAll();
	}

	@Override
	public Cliente guardar(Cliente cliente) {
		return repo.save(cliente);
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Cliente buscarPorId(Integer id) {
	    Optional<Cliente> optional = repo.findById(id);
	    //valor de return donde se utiliza el metodo orElse de Optional
	    // Si "optional" tiene un valor ,quiere decir que encontró una entidad con el ID indicado entonces devuelve el valor
	    // En caso de que "optional" se encuentra vacio, quiere decir que no se encuentra ninguna entidad con el ID indicado,por lo que devuelve un "null"
	    return optional.orElse(null);
	}

	@Override
	public boolean existe(Integer id) {
		return(id ==null)? false:repo.existsById(id);
	}

	@Override
	public List<Cliente> buscarPorActivo(boolean activo) {
		return repo.findByActivo(activo);
	}



		
}