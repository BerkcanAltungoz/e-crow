package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ChatMessageDao extends JpaRepository<ChatMessage, Integer> {
    boolean existsByFkEmployeeId(Integer employeeId);
    boolean existsByFkCustomerId(Integer customerId);
    boolean existsByFkCustomerIdAndFkEmployeeId(Integer customerId, Integer employeeId);
    List<ChatMessage> getByFkEmployeeId(Integer employeeId);
    List<ChatMessage> getByFkCustomerId(Integer customerId);
    List<ChatMessage> getByFkCustomerIdAndFkEmployeeId(Integer customerId, Integer employeeId);
}
