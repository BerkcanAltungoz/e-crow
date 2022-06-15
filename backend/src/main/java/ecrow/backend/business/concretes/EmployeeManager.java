package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.EmployeeService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.CityDao;
import ecrow.backend.dataAccess.concretes.EmployeeDao;
import ecrow.backend.dataAccess.concretes.TownDao;
import ecrow.backend.entities.concretes.Employee;
import ecrow.backend.entities.dtos.EmployeeAddDto;
import ecrow.backend.entities.dtos.EmployeeBalanceUpdateDto;
import ecrow.backend.entities.dtos.EmployeeBaseUpdateDto;
import ecrow.backend.entities.dtos.EmployeeDetailsUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManager implements EmployeeService {
    private final EmployeeDao employeeDao;
    private final TownDao townDao;
    private final CityDao cityDao;

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao, TownDao townDao, CityDao cityDao) {
        this.employeeDao = employeeDao;
        this.townDao = townDao;
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<List<Employee>> getAll() {
        return new SuccessDataResult<>(employeeDao.findAll());
    }

    @Override
    public DataResult<Employee> getById(Integer id) {
        return new SuccessDataResult<>(employeeDao.findById(id).get());
    }

    @Override
    public DataResult<Employee> getByEmail(String email) {
        if(!employeeDao.existsByEmail(email)){
            return new ErrorDataResult<>("Employee Not Found");
        }
        return new SuccessDataResult<>(employeeDao.getByEmail(email));
    }

    @Override
    public DataResult<Employee> getByPhoneNumber(String phoneNumber) {
        if(!employeeDao.existsByPhoneNumber(phoneNumber)){
            return new ErrorDataResult<>("Employee Not Found");
        }
        return new SuccessDataResult<>(employeeDao.getByPhoneNumber(phoneNumber));
    }

    @Override
    public DataResult<List<Employee>> getByFkTownId(Integer townId) {
        if(!employeeDao.existsByFkTownId(townId)){
            return new ErrorDataResult<>("Employee Not Found");
        }
        return new SuccessDataResult<>(employeeDao.getByFkTownId(townId));
    }

    @Override
    public DataResult<List<Employee>> getByFkCityId(Integer cityId) {
        if(!employeeDao.existsByFkCityId(cityId)){
            return new ErrorDataResult<>("Employee Not Found");
        }
        return new SuccessDataResult<>(employeeDao.getByFkCityId(cityId));
    }

    @Override
    public Result deleteById(Integer id) {
        if(!employeeDao.existsById(id)){
            return new ErrorDataResult<>("Employee Not Found");
        }
        employeeDao.deleteById(id);
        return new SuccessResult("Employee Deleted");
    }

    @Override
    public Result add(EmployeeAddDto employeeAddDto) {
        if(!employeeDao.existsByFkTownId(employeeAddDto.getFkTownId())){
            return new ErrorResult("Invalid Town Id");
        }
        else if(!employeeDao.existsByFkCityId(employeeAddDto.getFkCityId())){
            return new ErrorResult("Invalid City Id");
        }
        Employee employee = Employee.builder()
                .fkCity(cityDao.findById(employeeAddDto.getFkCityId()).get())
                .fkTown(townDao.findById(employeeAddDto.getFkTownId()).get())
                .email(employeeAddDto.getEmail())
                .password(employeeAddDto.getPassword())
                .name(employeeAddDto.getName())
                .surname(employeeAddDto.getSurname())
                .phoneNumber(employeeAddDto.getPhoneNumber())
                .build();
        employeeDao.save(employee);
        return new SuccessResult("Employee Saved");
    }

    @Override
    public Result updateBase(EmployeeBaseUpdateDto employeeBaseUpdateDto) {
        if(!employeeDao.existsById(employeeBaseUpdateDto.getId())){
            return new ErrorResult("Employee Not Found");
        }
        Employee employee = employeeDao.findById(employeeBaseUpdateDto.getId()).get();
        employee.setEmail(employeeBaseUpdateDto.getEmail());
        employee.setPassword(employeeBaseUpdateDto.getPassword());
        employee.setName(employee.getName());
        employee.setSurname(employee.getSurname());
        employee.setPhoneNumber(employee.getPhoneNumber());
        return new SuccessResult("Customer Updated");
    }

    @Override
    public Result updateDetails(EmployeeDetailsUpdateDto employeeDetailsUpdateDto) {
        if(!employeeDao.existsById(employeeDetailsUpdateDto.getId())){
            return new ErrorResult("Employee Not Found");
        }
        Employee employee = employeeDao.findById(employeeDetailsUpdateDto.getId()).get();
        employee.setFkCity(cityDao.findById(employeeDetailsUpdateDto.getFkCityId()).get());
        employee.setFkTown(townDao.findById(employeeDetailsUpdateDto.getFkTownId()).get());
        employee.setExpertise(employeeDetailsUpdateDto.getExpertise());
        employee.setFee(employeeDetailsUpdateDto.getFee());
        employee.setExpertiseFee(employeeDetailsUpdateDto.getExpertiseFee());
        employee.setAvailable(employeeDetailsUpdateDto.getAvailable());
        employee.setDescription(employee.getDescription());
        return new SuccessResult("Employee Details Updated");
    }

    @Override
    public Result updateBalance(EmployeeBalanceUpdateDto employeeBalanceUpdateDto) {
        if(!employeeDao.existsById(employeeBalanceUpdateDto.getId())){
            return new ErrorResult("Employee Not Found");
        }
        Employee employee = employeeDao.findById(employeeBalanceUpdateDto.getId()).get();
        employee.setBalance(employeeBalanceUpdateDto.getBalance());
        return new SuccessResult("Employee Balance Updated");
    }
}
