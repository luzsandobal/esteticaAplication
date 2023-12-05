package imb.pr3.estetica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "metodoDePago")
public class MetodoDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @Column(name = "habilitado")
    private Boolean habilitado;

    public MetodoDePago() {
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public String toString() {
        return "MetodoDePago{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", habilitado=" + habilitado +
                '}';
    }
}
