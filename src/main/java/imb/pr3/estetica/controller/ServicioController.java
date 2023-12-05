package imb.pr3.estetica.controller;

import imb.pr3.estetica.entity.Servicio;
import imb.pr3.estetica.service.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "estetica/servicio")
public class ServicioController {
    @Autowired
    private IServicioService servicioService;


    @GetMapping("")
    public ResponseEntity<APIResponse<List<Servicio>>> obtenerServicios() {
        try {
            List<Servicio> servicios = servicioService.buscarServicio();
            APIResponse<List<Servicio>> response = new APIResponse<>(HttpStatus.OK.value(), null, servicios);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir durante la obtención de servicios
            List<String> errorMessages = new ArrayList<>();
            System.out.println("error :" + e);
            errorMessages.add("Ocurrió un error al procesar la solicitud.");
            APIResponse<List<Servicio>> errorResponse = new APIResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessages, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Servicio>> getOne(@PathVariable Integer id) {
        try {
            Servicio servicio = servicioService.buscarServicioPorId(id);
            APIResponse<Servicio> response = new APIResponse<>(HttpStatus.OK.value(), null, servicio);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            List<String> errorList = new ArrayList<>();
            errorList.add("Error, Por Favor Intente más tarde.");
            APIResponse<Servicio> errorResponse = new APIResponse<>(HttpStatus.NOT_FOUND.value(), errorList, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping("")
    public ResponseEntity<APIResponse<Servicio>> guardarServicio(@RequestBody Servicio servicio) {
        try {
            if (servicio.getId() != null) {
                Servicio buscaServicio = servicioService.buscarServicioPorId(servicio.getId());
                if (buscaServicio != null) {
                    List<String> messages = new ArrayList<>();
                    messages.add("Ya existe un servicio con el ID " + servicio.getId().toString());
                    messages.add("Para actualizar debe utilizar el verbo PUT");
                    APIResponse<Servicio> response = new APIResponse<Servicio>(HttpStatus.BAD_REQUEST.value(), messages, null);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            }
            servicioService.guardarServicio(servicio);
            APIResponse<Servicio> response = new APIResponse<Servicio>(HttpStatus.CREATED.value(), null, servicio);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            // Aquí puedes manejar la excepción, por ejemplo, registrarla o devolver una respuesta de error personalizada.
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("Ocurrió un error al procesar la solicitud.");
            APIResponse<Servicio> errorResponse = new APIResponse<Servicio>(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessages, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Servicio>> actualizarServicio(@PathVariable Integer id, @RequestBody Servicio servicio) {
        try {
            // Verificar si el servicio con el ID especificado existe antes de actualizarlo
            Servicio buscaServicio = servicioService.buscarServicioPorId(id);
            if (buscaServicio == null) {
                List<String> messages = new ArrayList<>();
                messages.add("No se encontró un servicio con el ID " + id);
                APIResponse<Servicio> notFoundResponse = new APIResponse<Servicio>(HttpStatus.NOT_FOUND.value(), messages, null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
            }

            // Actualizar el servicio
            servicioService.guardarServicio(servicio);

            // Construir y devolver una respuesta exitosa
            APIResponse<Servicio> successResponse = new APIResponse<Servicio>(HttpStatus.OK.value(), null, servicio);
            return ResponseEntity.status(HttpStatus.OK).body(successResponse);
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir durante la actualización
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("Ocurrió un error al procesar la solicitud.");
            APIResponse<Servicio> errorResponse = new APIResponse<Servicio>(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessages, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> eliminarServicio(@PathVariable Integer id) {
        try {
            // Verificar si el servicio con el ID especificado existe antes de intentar eliminarlo
            Servicio buscaServicio = servicioService.buscarServicioPorId(id);
            if (buscaServicio == null) {
                List<String> messages = new ArrayList<>();
                messages.add("No se encontró un servicio con el ID " + id);
                APIResponse<Void> notFoundResponse = new APIResponse<Void>(HttpStatus.NOT_FOUND.value(), messages, null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
            }

            // Eliminar el servicio
            servicioService.eliminarServicio(id);

            // Construir y devolver una respuesta exitosa
            APIResponse<Void> successResponse = new APIResponse<Void>(HttpStatus.NO_CONTENT.value(), null, null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successResponse);
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir durante la eliminación
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("Ocurrió un error al procesar la solicitud.");
            APIResponse<Void> errorResponse = new APIResponse<Void>(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessages, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
