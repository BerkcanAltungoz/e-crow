package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmailAndPassword(String email, String password);
    Customer getByEmail(String email);
    Customer getByPhoneNumber(String phoneNumber);
    Customer getByEmailAndPassword(String email, String password);
}
