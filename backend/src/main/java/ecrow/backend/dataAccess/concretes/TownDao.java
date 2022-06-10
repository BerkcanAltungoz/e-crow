package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TownDao extends JpaRepository<Town, Integer> {
    List<Town> getByFkCityId(Integer cityId);
}
