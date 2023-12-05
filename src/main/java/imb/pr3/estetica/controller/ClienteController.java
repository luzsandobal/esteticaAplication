package imb.pr3.estetica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.estetica.entity.Cliente;
import imb.pr3.estetica.entity.MetodoDePago;
import imb.pr3.estetica.service.IClienteService;
import imb.pr3.estetica.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("api/estetica/cliente")
public class ClienteController {
	
	@Autowired
	IClienteService clienteService;
	
	//buscarTodos
	@GetMapping
	public ResponseEntity<APIResponse<List<Cliente>>> obtenerTodosLosClientes(){
		List<Cliente> cliente = clienteService.buscarTodos();
		return cliente.isEmpty() ? ResponseUtil.notFound("No hay clientes disponibles")
	           : ResponseUtil.success(cliente);
	}
	
	//buscarClientesActivos
	@GetMapping("/activos")
	public ResponseEntity<APIResponse<List<Cliente>>>obtenerTodosLosClientesActivos(){{
		List<Cliente> clientesActivos = clienteService.buscarPorActivo(true);
        return clientesActivos.isEmpty() ? ResponseUtil.notFound("No hay clientes activos")
	           : ResponseUtil.success(clientesActivos);
		}
	}
	
	//buscarClientesInactivos
		@GetMapping("/inactivos")
		public ResponseEntity<APIResponse<List<Cliente>>>obtenerTodosLosClientesHabilitados(){{
			List<Cliente> clientesInactivos = clienteService.buscarPorActivo(false);
	        return clientesInactivos.isEmpty() ? ResponseUtil.notFound("No hay clientes inactivos")
		           : ResponseUtil.success(clientesInactivos);
			}
		}
		
	
	
	
		
	//buscarPorId
	@GetMapping("{id}")
	public ResponseEntity<APIResponse<Cliente>> buscarClientePorId(@PathVariable("id") Integer id){
		Cliente cliente =  clienteService.buscarPorId(id);
		return cliente == null ? ResponseUtil.notFound("No se encontró el cliente con el identificador proporcionado")
				: ResponseUtil.success(cliente);	
	}
	
	
	//crearNuevo
	@PostMapping
    public ResponseEntity<APIResponse<Cliente>> crearCliente(@RequestBody Cliente cliente, BindingResult result) {
        return clienteService.existe(cliente.getId()) ? ResponseUtil.badRequest("No se puede crear cliente, el ID ingresado ya existe")
        		: ResponseUtil.success(cliente);
     }
	
	//Actualizar
	@PutMapping
	public ResponseEntity<APIResponse<Cliente>> modificarCliente(@RequestBody Cliente cliente) {
		return clienteService.existe(cliente.getId()) ? ResponseUtil.success(clienteService.guardar(cliente))
				: ResponseUtil.badRequest("No se puede actualizar cliente, el ID ingresado no ha sido creado");
		}
	
	//Eliminar
	//Anotación que indica que este método manejará solicitudes HTTP DELETE en una URL que utiliza "id"  como parámetro que se pasará en el body de la consulta
	@DeleteMapping("{id}")
	//Se define el método "eliminarCliente" que toma como parametro de entrada un "id" tipo Integer
	// El metodo devuelve un objeto ResponseEntity que contiene un objeto APIResponse de tipo Cliente
	//La anotación "@PathVariable" indica que el valor de "id" se tomará de la URL como parametro de ruta.Este valor se convertirá automáticamente en un objeto 
	//Integer llamado "id" y se pasará como argumento al método
	public ResponseEntity<APIResponse<Cliente>> eliminarCliente(@PathVariable("id") Integer id) {
		//Se llama al método "existe" del servicio "clienteService" para verificar si el ID del cliente existe. 
		if (clienteService.existe(id)) {
			// Si la condición "clienteService.existe(id)" es verdadera, quiere decir que el cliente existe, por lo tanto, se elimina al cliente identificado por el ID
			clienteService.eliminar(id);
			// Devuelve como respuesta un mensaje HTTP successDeleted avisando que el cliente fue eliminado correctamente
			return ResponseUtil.successDeleted("El cliente con ID " + id+ " fue eliminado exitosamente");
		}else {
			// Si la condición "clienteService.existe(id)" es falsa, significa que el cliente no existe, por lo que se genera una respuesta HTTP de error BadRequest con un mensaje que indica que el cliente no existe
    		//Esto se envuelve en un objeto ResponseEntity que contiene un objeto APIResponse de tipo Cliente
			return ResponseUtil.badRequest("No existe el cliente con el ID ingresado");
		}
	}
		
		
	// Excepciones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Cliente>> handleException(Exception ex) {    	
    	return ResponseUtil.badRequest(ex.getMessage());
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Cliente>> handleConstraintViolationException(ConstraintViolationException ex) {
    	return ResponseUtil.handleConstraintException(ex);
    } 
   
}