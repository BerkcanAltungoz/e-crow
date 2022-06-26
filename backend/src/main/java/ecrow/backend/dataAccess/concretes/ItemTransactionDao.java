package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.ItemTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ItemTransactionDao extends JpaRepository<ItemTransaction, Integer> {
    Boolean existsByFkEmployeeId(Integer employeeId);
    Boolean existsByFkBuyerId(Integer buyerId);
    Boolean existsByFkSellerId(Integer sellerId);
    List<ItemTransaction> getByFkEmployeeId(Integer employeeId);
    List<ItemTransaction> getByFkBuyerId(Integer buyerId);
    List<ItemTransaction> getByFkSellerId(Integer sellerId);

    @Query("From ItemTransaction order by fkStatus.name ")
    List<ItemTransaction> getAllByOrderByFkStatus_NameAsc();


}
