package imb.pr3.estetica.entity;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "ordenDePago")
public class OrdenDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fecha_pago")
    private Date fecha_pago;

    @Column(name = "total")
    private Double total;
    @ManyToOne
    @JoinColumn(name = "metodoDePagoId")
    private MetodoDePago metodoDePagoId;

    public OrdenDePago() {
    }

    public OrdenDePago(Integer id, Date fecha_pago, Double total, MetodoDePago metodoDePagoId) {
        this.id = id;
        this.fecha_pago = fecha_pago;
        this.total = total;
        this.metodoDePagoId = metodoDePagoId;
    }

    public MetodoDePago getMetodoDePagoId() {
        return metodoDePagoId;
    }

    public void setMetodoDePagoId(MetodoDePago metodoDePagoId) {
        this.metodoDePagoId = metodoDePagoId;
    }

    public Integer getId() {
        return id;
    }


    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
