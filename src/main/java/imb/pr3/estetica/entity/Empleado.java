 package imb.pr3.estetica.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellido;
	private String correo;
	private String domicilio;
	private String genero;
	private String cargo_laboral;
	private Long numero_telefono;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	 public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getCargo_laboral() {
		return cargo_laboral;
	}
	public void setCargo_laboral(String cargo_laboral) {
		this.cargo_laboral = cargo_laboral;
	}
	
	public Long getNumero_telefono() {
		return numero_telefono;
	}
	public void setNumero_telefono(Long numero_telefono) {
		this.numero_telefono = numero_telefono;
	}
	
	
	
}
