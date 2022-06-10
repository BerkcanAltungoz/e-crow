package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CityDao extends JpaRepository<City, Integer> {
    City getByName(String name);
    boolean existsByName(String name);
}
