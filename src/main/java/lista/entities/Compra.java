package lista.entities;

import javax.persistence.*;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @Column(name = "idCompra")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCompra;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cantidad")
    private Double cantidad;

    public Compra(long idCompra, String descripcion, String estado, Double cantidad, Producto precio) {
        this.idCompra = idCompra;
        this.descripcion = descripcion;
        this.estado = estado;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Producto getPrecio() {
        return precio;
    }

    public void setPrecio(Producto precio) {
        this.precio = precio;
    }

    @JoinColumn(name="precioId")
    @OneToOne(cascade=CascadeType.ALL)
    private Producto precio;

    public Compra(){}


    public long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(long idCompra) {
        this.idCompra = idCompra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }


}
