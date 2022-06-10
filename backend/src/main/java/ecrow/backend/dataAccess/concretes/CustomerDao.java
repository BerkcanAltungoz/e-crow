package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
}
