package cf.soisi.suchtrupp_erfassung.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Suche implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String bereich;

    @OneToMany(mappedBy = "suche")
    private List<Suchtrupp> suchtruppList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Suche suche = (Suche) o;
        return Objects.equals(id, suche.id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    public Suche(String name, String bereich) {
        this.name = name;
        this.bereich = bereich;
        this.suchtruppList = new ArrayList<>();
    }
}
