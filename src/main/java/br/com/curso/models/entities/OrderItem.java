package br.com.curso.models.entities;

import br.com.curso.models.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_ORDER_ITEM")
//Não posso utilizar o lombok para os gets e sets pelo menos
public class OrderItem implements Serializable {

    private static final long serialVesionUID = 1L;

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();
    @Column(name = "QT_PRODUCT")
    private Integer quantity;
    @Column(name = "VL_PRODUCT")
    private Double price;

    public OrderItem(){

    }

    public OrderItem(Product product, Order order, Integer quantity, Double price){
        id.setProduct(product);
        id.setOrder(order);
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder(){
        return id.getOrder();
    }

    public void setOrder(Order order){
        id.setOrder(order);
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id.equals(orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
