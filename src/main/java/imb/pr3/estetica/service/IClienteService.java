package imb.pr3.estetica.service;

import java.util.List;

import imb.pr3.estetica.entity.Cliente;

public interface IClienteService {
	Cliente buscarPorId(Integer id);
	List<Cliente> buscarTodos();
	public Cliente guardar(Cliente cliente);
	public void eliminar(Integer id);
	public boolean existe(Integer id);
	public List<Cliente> buscarPorActivo(boolean activo);
}
