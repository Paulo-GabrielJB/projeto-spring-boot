package br.com.curso.models.entities.pk;

import br.com.curso.models.entities.Order;
import br.com.curso.models.entities.Product;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class OrderItemPK implements Serializable {

    private static final long serialVesionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "ID_ORDER")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "ID_PRODUCT")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return order.equals(that.order) &&
                product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
