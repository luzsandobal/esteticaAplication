package imb.pr3.estetica.controller;

import imb.pr3.estetica.entity.MetodoDePago;
import imb.pr3.estetica.service.IMetodoDePagoService;
import imb.pr3.estetica.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/api/estetica/metodo_de_pago")
public class MetodoDePagoController {

	@Autowired
	private IMetodoDePagoService metodoDePagoService;


	@GetMapping("")
	public ResponseEntity<APIResponse<List<MetodoDePago>>> obtenerTodosMetodosDePago() {
		List<MetodoDePago> metodosDePago = metodoDePagoService.buscarTodos();
		return metodosDePago.isEmpty() ? ResponseUtil.notFound("No hay métodos de Pago")
				: ResponseUtil.success(metodosDePago);
	}

	@GetMapping("{id}")
	public ResponseEntity<APIResponse<MetodoDePago>> obtenerMetodoDePagoPorId(@PathVariable("id") Integer id){
		MetodoDePago metodoDePago = metodoDePagoService.buscarPorId(id);
		return metodoDePago == null ? ResponseUtil.notFound("No se encontró el método de pago con el identificador proporcionado")
				: ResponseUtil.success(metodoDePago);
	}

	@PostMapping
	public ResponseEntity<APIResponse<MetodoDePago>> crearMetodoDePago(@RequestBody MetodoDePago metodoDePago, BindingResult result) {
		return metodoDePagoService.existe(metodoDePago.getId()) ? ResponseUtil.badRequest("Ya existe un método de pago con el identificador proporcionado")
				: ResponseUtil.created(metodoDePagoService.guardar(metodoDePago));

	}

	@PutMapping
	public ResponseEntity<APIResponse<MetodoDePago>> modificarMetodoDePago(@RequestBody MetodoDePago metodoDePago) {
		return metodoDePagoService.existe(metodoDePago.getId()) ? ResponseUtil.success(metodoDePagoService.guardar(metodoDePago))
				: ResponseUtil.badRequest("No existe el método de pago con el identificador proporcionado");
	}

	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<MetodoDePago>> deleteTask(@PathVariable("id") Integer id){
		if (metodoDePagoService.existe(id)) {
			metodoDePagoService.eliminar(id);  // Eliminar el servicio con el ID proporcionado
			return ResponseUtil.successDeleted("se logro borrar con exito el Método de Pago");  // Se ha eliminado exitosamente
		} else {
			return ResponseUtil.badRequest("No existe el método de pago con el identificador proporcionado");
		}
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<MetodoDePago>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<MetodoDePago>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}

}