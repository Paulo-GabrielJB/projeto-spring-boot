package br.com.curso.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PRODUCT")
public class Product implements Serializable {

    private static final long serialVesionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCT")
    private Long id;
    @Column(name = "NM_PRODUCT")
    private String name;
    @Column(name = "DS_PRODUCT")
    private String description;
    @Column(name = "VL_PRODUCT")
    private Double price;
    @Column(name = "IMG_PRODUCT")
    private String imgUrl;

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "TB_PRODUCT_CATEGORY",
            joinColumns = @JoinColumn(name = "ID_PRODUCT"),
            inverseJoinColumns = @JoinColumn(name = "ID_CATEGORY"))
    private Set<Category> categories = new HashSet<>();

    @JsonIgnore
    public Set<Order> getOrders(){
        Set<Order> set = new HashSet<>();
        for(OrderItem x: items){
            set.add(x.getOrder());
        }
        return set;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
