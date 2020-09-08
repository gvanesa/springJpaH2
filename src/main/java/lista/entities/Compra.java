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

   /* @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_precio")
    private Producto precio;*/

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

    /*public Producto getPrecio() {
        return precio;
    }

    public void setPrecio(Producto precio) {
        this.precio = precio;
    }*/
}
