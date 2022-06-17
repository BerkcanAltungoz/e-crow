package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface RequirementDao extends JpaRepository<Requirement, Integer> {
    boolean existsByFkItemTransactionId(Integer itemTransactionId);
    List<Requirement> getByFkItemTransactionId(Integer itemTransactionId);
}
