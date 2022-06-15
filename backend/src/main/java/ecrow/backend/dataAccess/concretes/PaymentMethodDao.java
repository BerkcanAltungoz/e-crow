package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface PaymentMethodDao extends JpaRepository<PaymentMethod, Integer> {
    boolean existsByFkCustomerId(Integer customerId);
    boolean existsByCardNumber(String cardNumber);
    List<PaymentMethod> getByFkCustomerId(Integer customerId);
}
