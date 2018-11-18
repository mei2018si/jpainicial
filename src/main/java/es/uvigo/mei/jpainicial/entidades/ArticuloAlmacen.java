package es.uvigo.mei.jpainicial.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ArticuloAlmacenId.class)
public class ArticuloAlmacen implements Serializable {

    @Id
    @ManyToOne
    private Articulo articulo;

    @Id
    @ManyToOne
    private Almacen almacen;
    
    private Integer stock;

    public ArticuloAlmacen() {
    }

    public ArticuloAlmacen(Articulo articulo, Almacen almacen, Integer stock) {
        this.articulo = articulo;
        this.almacen = almacen;
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.articulo);
        hash = 53 * hash + Objects.hashCode(this.almacen);
        hash = 53 * hash + Objects.hashCode(this.stock);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArticuloAlmacen other = (ArticuloAlmacen) obj;
        if (!Objects.equals(this.articulo, other.articulo)) {
            return false;
        }
        if (!Objects.equals(this.almacen, other.almacen)) {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        return true;
    }
    
    

}
