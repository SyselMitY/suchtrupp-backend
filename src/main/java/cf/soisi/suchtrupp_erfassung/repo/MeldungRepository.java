package cf.soisi.suchtrupp_erfassung.repo;

import cf.soisi.suchtrupp_erfassung.entity.Meldung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeldungRepository extends JpaRepository<Meldung,Integer> {

}
