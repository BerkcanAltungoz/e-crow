package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.City;
import ecrow.backend.entities.concretes.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface StatusDao extends JpaRepository<Status, Integer> {
    Status getByName(String name);
    boolean existsByName(String name);
}
