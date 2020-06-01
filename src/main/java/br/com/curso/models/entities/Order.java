package br.com.curso.models.entities;


import br.com.curso.models.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ORDER")
public class Order implements Serializable {

    private static final long serialVesionUID = 1L;

    @Id
    @Column(name = "ID_ORDER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DT_ORDER")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant momemt;
    @Column(name = "ST_ORDER")
    private Integer orderStatus;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private User client;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public OrderStatus getOrderStatus(){
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus.getCode();
    }

    public Double getTotal(){
        Double sum = 0.0;
        for(OrderItem oi: items)
            sum += oi.getSubTotal();
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
