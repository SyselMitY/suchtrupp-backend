package cf.soisi.suchtrupp_erfassung.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Meldung implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String beschreibung;

    @NotNull
    private String tags;

    @NotNull
    private Instant timestamp;

    @ManyToOne(optional = false)
    @JsonIgnore
    private Suchtrupp suchtrupp;

    public Meldung(String beschreibung, String tags,Suchtrupp suchtrupp) {
        this.beschreibung = beschreibung;
        this.tags = tags;
        this.suchtrupp = suchtrupp;
        this.suchtrupp.getMeldungen().add(this);
        this.timestamp = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Meldung meldung = (Meldung) o;
        return Objects.equals(id, meldung.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
