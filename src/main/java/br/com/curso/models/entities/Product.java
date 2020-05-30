package br.com.curso.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
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

    @Transient
    private Set<Category> categories = new HashSet<>();

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
