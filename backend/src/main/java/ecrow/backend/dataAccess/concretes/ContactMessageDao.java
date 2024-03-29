package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ContactMessageDao extends JpaRepository<ContactMessage, Integer> {
}
