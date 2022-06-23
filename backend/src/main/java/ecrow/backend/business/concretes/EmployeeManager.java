package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.EmployeeService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.BaseUserDao;
import ecrow.backend.dataAccess.concretes.CityDao;
import ecrow.backend.dataAccess.concretes.EmployeeDao;
import ecrow.backend.dataAccess.concretes.TownDao;
import ecrow.backend.entities.concretes.Employee;
import ecrow.backend.entities.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeManager implements EmployeeService {
    private final EmployeeDao employeeDao;
    private final TownDao townDao;
    private final CityDao cityDao;
    private final BaseUserDao baseUserDao;

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao, TownDao townDao, CityDao cityDao, BaseUserDao baseUserDao) {
        this.employeeDao = employeeDao;
        this.townDao = townDao;
        this.cityDao = cityDao;
        this.baseUserDao = baseUserDao;
    }

    @Override
    public DataResult<List<Employee>> getAll() {
        return new SuccessDataResult<>(employeeDao.findAll());
    }

    @Override
    public DataResult<Employee> getById(Integer id) {
        if(!employeeDao.existsById(id)){
            return new ErrorDataResult<>("Employee Not Found");
        }
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
    public DataResult<Employee> signIn(SignInDto signInDto) {
        if(!employeeDao.existsByEmail(signInDto.getEmail())){
            return new ErrorDataResult<>("Email Does Not Exist");
        }
        else if(!employeeDao.existsByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword())){
            return new ErrorDataResult<>("Wrong Password");
        }
        return new SuccessDataResult<>(employeeDao.getByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword()));
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
        if(!townDao.existsById(employeeAddDto.getFkTownId())){
            return new ErrorResult("Invalid Town Id");
        }
        else if(!cityDao.existsById(employeeAddDto.getFkCityId())){
            return new ErrorResult("Invalid City Id");
        }
        else if(baseUserDao.existsByEmail(employeeAddDto.getEmail())){
            return new ErrorResult("Email Already In Use");
        }
        else if(baseUserDao.existsByPhoneNumber(employeeAddDto.getPhoneNumber())){
            return new ErrorResult("Phone Number Already In Use");
        }
        Employee employee = Employee.builder()
                .fkCity(cityDao.findById(employeeAddDto.getFkCityId()).get())
                .fkTown(townDao.findById(employeeAddDto.getFkTownId()).get())
                .email(employeeAddDto.getEmail())
                .password(employeeAddDto.getPassword())
                .name(employeeAddDto.getName())
                .surname(employeeAddDto.getSurname())
                .phoneNumber(employeeAddDto.getPhoneNumber())
                .balance(0)
                .dateCreated(LocalDateTime.now())
                .available(true)
                .description(" ")
                .emailValidation(false)
                .phoneValidation(false)
                .expertise(" ")
                .expertiseFee(0)
                .fee(0)
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
        employeeDao.save(employee);
        return new SuccessResult("Customer Updated");
    }

    @Override
    public Result updateDetails(EmployeeDetailsUpdateDto employeeDetailsUpdateDto) {
        if(!employeeDao.existsById(employeeDetailsUpdateDto.getId())){
            return new ErrorResult("Employee Not Found");
        }
        else if(!employeeDao.existsByFkTownId(employeeDetailsUpdateDto.getFkTownId())){
            return new ErrorResult("Invalid Town Id");
        }
        else if(!employeeDao.existsByFkCityId(employeeDetailsUpdateDto.getFkCityId())){
            return new ErrorResult("Invalid City Id");
        }
        Employee employee = employeeDao.findById(employeeDetailsUpdateDto.getId()).get();
        employee.setFkCity(cityDao.findById(employeeDetailsUpdateDto.getFkCityId()).get());
        employee.setFkTown(townDao.findById(employeeDetailsUpdateDto.getFkTownId()).get());
        employee.setExpertise(employeeDetailsUpdateDto.getExpertise());
        employee.setFee(employeeDetailsUpdateDto.getFee());
        employee.setExpertiseFee(employeeDetailsUpdateDto.getExpertiseFee());
        employee.setAvailable(employeeDetailsUpdateDto.getAvailable());
        employee.setDescription(employee.getDescription());
        employeeDao.save(employee);
        return new SuccessResult("Employee Details Updated");
    }

    @Override
    public Result updateBalance(EmployeeBalanceUpdateDto employeeBalanceUpdateDto) {
        if(!employeeDao.existsById(employeeBalanceUpdateDto.getId())){
            return new ErrorResult("Employee Not Found");
        }
        Employee employee = employeeDao.findById(employeeBalanceUpdateDto.getId()).get();
        employee.setBalance(employeeBalanceUpdateDto.getBalance());
        employeeDao.save(employee);
        return new SuccessResult("Employee Balance Updated");
    }
}
