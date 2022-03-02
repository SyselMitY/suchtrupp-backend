package cf.soisi.suchtrupp_erfassung.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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
public class Suchtrupp implements Serializable {
    @Id
    @GeneratedValue()
    private Integer id;

    @NotBlank
    private String leiter;

    private int anzahlPersonen;

    @ManyToOne
    @JsonIgnore
    private Suche suche;

    @OneToMany(mappedBy = "suchtrupp")
    @ToString.Exclude
    private List<Meldung> meldungen;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Suchtrupp suchtrupp = (Suchtrupp) o;
        return Objects.equals(id, suchtrupp.id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    public Suchtrupp(String leiter, int anzahlPersonen, Suche suche) {
        this.leiter = leiter;
        this.anzahlPersonen = anzahlPersonen;
        this.suche = suche;
        this.suche.getSuchtruppList().add(this);
        this.meldungen = new ArrayList<>();
    }
}
