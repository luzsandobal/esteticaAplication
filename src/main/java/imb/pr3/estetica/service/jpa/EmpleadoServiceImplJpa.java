package imb.pr3.estetica.service.jpa;

import java.util.List;
import java.util.Optional;

import imb.pr3.estetica.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.estetica.entity.Empleado;
import imb.pr3.estetica.repository.EmpleadoRepository;


@Service
public class EmpleadoServiceImplJpa implements IEmpleadoService {

	@Autowired
	private EmpleadoRepository repositoryEmpleado;
	
	
	@Override
	public List<Empleado> buscarTodos() {
		return repositoryEmpleado.findAll();
	}

@Override
	public Empleado buscarPorId(int id) {
		Optional<Empleado> optional = repositoryEmpleado.findById(id);
		return optional.orElse(null);
}
	@Override
	public Empleado guardar(Empleado empleado) {
		repositoryEmpleado.save(empleado);
		return empleado;
	}
	@Override
	public void eliminar(int id) {
		repositoryEmpleado.deleteById(id);
		
	}
	@Override
	public boolean existe(Integer id) {
		return id == null ? false : repositoryEmpleado.existsById(id);
	}
}
