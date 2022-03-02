package cf.soisi.suchtrupp_erfassung.repo;

import cf.soisi.suchtrupp_erfassung.entity.Suchtrupp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuchtruppRepository extends JpaRepository<Suchtrupp,Integer> {
}
