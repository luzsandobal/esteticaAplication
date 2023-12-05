package imb.pr3.estetica.controller;

import java.util.List;

import imb.pr3.estetica.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.estetica.entity.Empleado;
import imb.pr3.estetica.service.IEmpleadoService;

@RestController
@Validated
@RequestMapping("/api/estetica/empleado")
public class EmpleadoController {
	
	@Autowired
	private IEmpleadoService service;
	
	@GetMapping("")
	public ResponseEntity<APIResponse<List<Empleado>>> verEmpleados() {
		List<Empleado> empleados = service.buscarTodos();
		return (empleados.isEmpty()) ? ResponseUtil.notFound("La empresa no tiene empleados") : ResponseUtil.success(empleados);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<APIResponse<Empleado>> empleadoId(@PathVariable int id) {

		Empleado empleado = service.buscarPorId(id);
		return empleado == null ? ResponseUtil.notFound("No se encontro el numero de empleado") : ResponseUtil.success(empleado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<APIResponse<Empleado>> empleadoModificado(@RequestBody Empleado empleado , @PathVariable int id) {
		return service.existe(empleado.getId()) ? ResponseUtil.success(service.guardar(empleado)) : ResponseUtil.badRequest("No se pudo modificar el empleado");

	}

	@PostMapping("") /* Esto es una anotación de Spring Framework que se utiliza para mapear las solicitudes HTTP POST a un controlador en específico.
						  el valor que esté entre las dos comillas dobles y dentro de los paréntesis ("/post") indica que este método maneja las solicitudes
						  	POST en el path que se indica. */
	public ResponseEntity<APIResponse<Empleado>>  nuevoEmpleado(@RequestBody Empleado empleado) {
		/* La anotación @RequestBody le pide a Spring que debe convertir el tipo de formato que se esta pasando (sea XML , JSON) que se esta pasando atraves
		      de una solicitud HTTP , en un formato de tipo objeto , en este caso de tipo Empleado. */
		/* El método devuelve un ResponseEntity que envuelve un APIResponse que contiene un objeto de tipo Empleado siendo la respesta que se espera. */
		return service.existe(empleado.getId()) ? ResponseUtil.badRequest("YA EXISTE UN EMPLEADO CON ESE NUMERO") : ResponseUtil.created(service.guardar(empleado));
		/* En este caso con un operador ternario se valida con el metodo "existe()" , si existe el id pedido por parametro del mismo atraves del metodo "getId()".
		 	 Si existe entonces atraves de la clase "ResponseUtil" se informa con el metodo "badRequest" un mensaje de que ese "id" es existente , si no de los contrario ,
		 	   se utiliza el metodo "guardar" pasado por parametro en el metodo "created" para guardar en la base de datos el objeto de tipo "Empleado". */
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Empleado>> empleadoEliminado(@PathVariable int id) {
		if (service.existe(id)) {
			service.eliminar(id);
			return ResponseUtil.successDeleted("Se despidio el Empleado");
		} else {
			return ResponseUtil.badRequest("No existe el empleado con el identificador proporcionado");
		}
	}

	
	
	
}
