package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.CustomerService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.BaseUserDao;
import ecrow.backend.dataAccess.concretes.CustomerDao;
import ecrow.backend.entities.concretes.Customer;
import ecrow.backend.entities.dtos.CustomerAddDto;
import ecrow.backend.entities.dtos.CustomerBalanceUpdateDto;
import ecrow.backend.entities.dtos.CustomerBaseUpdateDto;
import ecrow.backend.entities.dtos.SignInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements CustomerService {
    private final CustomerDao customerDao;
    private final BaseUserDao baseUserDao;

    @Autowired
    public CustomerManager(CustomerDao customerDao, BaseUserDao baseUserDao) {
        this.customerDao = customerDao;
        this.baseUserDao = baseUserDao;
    }


    @Override
    public DataResult<List<Customer>> getAll() {
        return new SuccessDataResult<>(customerDao.findAll());
    }

    @Override
    public DataResult<Customer> getById(Integer id) {
        if(!customerDao.existsById(id)){
            return new ErrorDataResult<>("Customer Not Found");
        }
        return new SuccessDataResult<>(customerDao.findById(id).get());
    }

    @Override
    public DataResult<Customer> getByEmail(String email) {
        if(!customerDao.existsByEmail(email)){
            return new ErrorDataResult<>("Customer Not Found");
        }
        return new SuccessDataResult<>(customerDao.getByEmail(email));
    }

    @Override
    public DataResult<Customer> getByPhoneNumber(String phoneNumber) {
        if(!customerDao.existsByPhoneNumber(phoneNumber)){
            return new ErrorDataResult<>("Customer Not Found");
        }
        return new SuccessDataResult<>(customerDao.getByPhoneNumber(phoneNumber));
    }

    @Override
    public DataResult<Customer> signIn(SignInDto signInDto) {
        if(!customerDao.existsByEmail(signInDto.getEmail())){
            return new ErrorDataResult<>("Email Does Not Exist");
        }
        else if(!customerDao.existsByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword())){
            return new ErrorDataResult<>("Wrong Password");
        }
        return new SuccessDataResult<>(customerDao.getByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword()));
    }

    @Override
    public Result deleteById(Integer id) {
        if(!customerDao.existsById(id)){
            return new ErrorResult("Customer Not Found");
        }
        customerDao.deleteById(id);
        return new SuccessResult("Customer Deleted");
    }

    @Override
    public Result add(CustomerAddDto customerAddDto) {
        if(baseUserDao.existsByEmail(customerAddDto.getEmail())){
            return new ErrorResult("Email Already In Use");
        }
        else if(baseUserDao.existsByPhoneNumber(customerAddDto.getPhoneNumber())){
            return new ErrorResult("Phone Number Already In Use");
        }
//        Customer customer = Customer.builder()
//                .email(customerAddDto.getEmail())
//                .password(customerAddDto.getPassword())
//                .name(customerAddDto.getName())
//                .surname(customerAddDto.getSurname())
//                .phoneNumber(customerAddDto.getPhoneNumber())
//                .build();
        Customer customer = new Customer();
        customer.setEmail(customerAddDto.getEmail());
        customer.setPassword(customerAddDto.getPassword());
        customer.setName(customerAddDto.getName());
        customer.setSurname(customerAddDto.getSurname());
        customer.setPhoneNumber(customerAddDto.getPhoneNumber());
        System.out.println(customer.getId());
        customerDao.save(customer);
        return new SuccessResult("Customer Saved");
    }

    @Override
    public Result updateBase(CustomerBaseUpdateDto customerBaseUpdateDto) {
        if(!customerDao.existsById(customerBaseUpdateDto.getId())){
            return new ErrorResult("Customer Not Found");
        }
        Customer customer = customerDao.findById(customerBaseUpdateDto.getId()).get();
        customer.setEmail(customerBaseUpdateDto.getEmail());
        customer.setPassword(customerBaseUpdateDto.getPassword());
        customer.setName(customer.getName());
        customer.setSurname(customer.getSurname());
        customer.setPhoneNumber(customer.getPhoneNumber());
        customerDao.save(customer);
        return new SuccessResult("Customer Updated");
    }

    @Override
    public Result updateBalance(CustomerBalanceUpdateDto customerBalanceUpdateDto) {
        if(!customerDao.existsById(customerBalanceUpdateDto.getId())){
            return new ErrorResult("Customer Not Found");
        }
        Customer customer = customerDao.findById(customerBalanceUpdateDto.getId()).get();
        customer.setBalance(customerBalanceUpdateDto.getBalance());
        customerDao.save(customer);
        return new SuccessResult("Customer Balance Updated");
    }
}
