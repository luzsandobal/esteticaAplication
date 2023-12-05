package imb.pr3.estetica.service;

import java.util.List;

import imb.pr3.estetica.entity.Empleado;


public interface IEmpleadoService {
	List<Empleado> buscarTodos() ;
	public Empleado buscarPorId(int id) ;
	public void eliminar(int id);
	public Empleado guardar(Empleado empleado) ;
	public boolean existe(Integer id);

}
