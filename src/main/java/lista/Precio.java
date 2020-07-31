package lista;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Objects;

@Entity
class Precio
{
    private @Id @GeneratedValue Long id;
    private String articulo;
    private int valor;

    Precio(){}

    Precio(String articulo, int valor)
    {
        this.articulo=articulo;
        this.valor=valor;
    }

    public Long getId() {
        return id;
    }

    public String getArticulo() {
        return articulo;
    }

    public int getValor() {
        return valor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Precio))
            return false;
        Precio employee = (Precio) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.articulo, employee.articulo)
                && Objects.equals(this.valor, employee.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id,articulo, valor);
    }



    public String toString(){
        return '\t'+"Artículo: " + articulo + '\t'+'\t'+"Valor: " + valor;
    }








}

