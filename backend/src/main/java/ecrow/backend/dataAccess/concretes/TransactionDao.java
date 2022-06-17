package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {
    boolean existsByFkEmployeeId(Integer employeeId);
    boolean existsByFkBuyerId(Integer buyerId);
    boolean existsByFkSellerId(Integer sellerId);
    List<Transaction> getByFkEmployeeId(Integer employeeId);
    List<Transaction> getByFkBuyerId(Integer buyerId);
    List<Transaction> getByFkSellerId(Integer sellerId);

    @Query("From Transaction order by fkStatus.name ")
    List<Transaction> getAllByOrderByFkStatus_NameAsc();


}
