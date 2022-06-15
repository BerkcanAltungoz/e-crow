package ecrow.backend.dataAccess.concretes;

import ecrow.backend.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    boolean existsByFkCityId(Integer customerId);
    boolean existsByFkTownId(Integer townId);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    List<Employee> getByFkTownId(Integer townId);
    List<Employee> getByFkCityId(Integer cityId);
    Employee getByEmail(String email);
    Employee getByPhoneNumber(String phoneNumber);

}
