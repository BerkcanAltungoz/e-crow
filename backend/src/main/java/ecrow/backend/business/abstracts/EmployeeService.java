package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.Employee;
import ecrow.backend.entities.dtos.*;

import java.util.List;

public interface EmployeeService {
    DataResult<List<Employee>> getAll();
    DataResult<Employee> getById(Integer id);
    DataResult<Employee> getByEmail(String email);
    DataResult<Employee> getByPhoneNumber(String phoneNumber);
    DataResult<List<Employee>> getByFkTownId(Integer townId);
    DataResult<List<Employee>> getByFkCityId(Integer cityId);
    DataResult<List<Employee>> getAllByAvailableIsTrue();
    DataResult<List<Employee>> getByFkTownIdAndAvailableIsTrue(Integer townId);
    DataResult<List<Employee>> getByFkCityIdAndAvailableIsTrue(Integer cityId);
    DataResult<Employee> signIn(SignInDto signInDto);
    Result deleteById(Integer id);

    Result add(EmployeeAddDto employeeAddDto);
    Result updateBase(EmployeeBaseUpdateDto employeeBaseUpdateDto);
    Result updateDetails(EmployeeDetailsUpdateDto employeeDetailsUpdateDto);
    Result updateBalance(EmployeeBalanceUpdateDto employeeBalanceUpdateDto);
}
