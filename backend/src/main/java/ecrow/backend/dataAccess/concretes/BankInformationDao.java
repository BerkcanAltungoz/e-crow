package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.BankInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BankInformationDao extends JpaRepository<BankInformation, Integer> {
    boolean existsByIban(String iban);
    boolean existsByFkUserId(Integer userId);
    List<BankInformation> getByFkUserId(Integer userId);
}
