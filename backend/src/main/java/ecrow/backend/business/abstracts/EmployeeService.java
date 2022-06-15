package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.Employee;
import ecrow.backend.entities.dtos.EmployeeAddDto;
import ecrow.backend.entities.dtos.EmployeeBalanceUpdateDto;
import ecrow.backend.entities.dtos.EmployeeBaseUpdateDto;
import ecrow.backend.entities.dtos.EmployeeDetailsUpdateDto;

import java.util.List;

public interface EmployeeService {
    DataResult<List<Employee>> getAll();
    DataResult<Employee> getById(Integer id);
    DataResult<Employee> getByEmail(String email);
    DataResult<Employee> getByPhoneNumber(String phoneNumber);
    DataResult<List<Employee>> getByFkTownId(Integer townId);
    DataResult<List<Employee>> getByFkCityId(Integer cityId);
    Result deleteById(Integer id);

    Result add(EmployeeAddDto employeeAddDto);
    Result updateBase(EmployeeBaseUpdateDto employeeBaseUpdateDto);
    Result updateDetails(EmployeeDetailsUpdateDto employeeDetailsUpdateDto);
    Result updateBalance(EmployeeBalanceUpdateDto employeeBalanceUpdateDto);
}
