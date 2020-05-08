package br.com.curso.models.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USER")
public class User implements Serializable {

    private static final long serialVesionUID = 1L;

    @Id
    @Column(name = "ID_USER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(min = 5, max = 70)
    @Column(name = "DS_NAME", nullable = false, length = 70)
    private String name;
    @Length(min = 10, max = 80)
    @Column(name = "DS_EMAIL", nullable = false, length = 80)
    private String email;
    @Length(min = 10, max = 11)
    @Column(name = "NR_PHONE", nullable = false, length = 11)
    private String phone;
    @Column(name = "DS_PASSWORD", nullable = false)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
