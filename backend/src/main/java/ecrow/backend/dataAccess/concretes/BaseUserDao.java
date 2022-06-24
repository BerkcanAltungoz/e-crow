package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BaseUserDao extends JpaRepository<BaseUser, Integer> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    BaseUser getByPhoneNumber(String phoneNumber);
    BaseUser getByEmail(String email);
}
