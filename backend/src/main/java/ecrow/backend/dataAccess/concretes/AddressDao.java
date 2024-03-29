package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {
    boolean existsByFkCustomerId(Integer customerId);
    boolean existsByFkTownId(Integer townId);
    boolean existsByFkCityId(Integer cityId);
    List<Address> getByFkCustomerId(Integer customerId);
}