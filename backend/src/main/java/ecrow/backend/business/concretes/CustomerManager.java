package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.CustomerService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.BaseUserDao;
import ecrow.backend.dataAccess.concretes.CustomerDao;
import ecrow.backend.entities.concretes.Customer;
import ecrow.backend.entities.dtos.CustomerAddDto;
import ecrow.backend.entities.dtos.CustomerDepositBalanceDto;
import ecrow.backend.entities.dtos.CustomerBaseUpdateDto;
import ecrow.backend.entities.dtos.SignInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    //TODO: CHECK IF EMAIL VERIFIED
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

    //TODO: SEND EMAIL VERIFICATION & HASH PASSWORD
    @Override
    public Result add(CustomerAddDto customerAddDto) {
        if(baseUserDao.existsByEmail(customerAddDto.getEmail())){
            return new ErrorResult("Email Already In Use");
        }
        else if(baseUserDao.existsByPhoneNumber(customerAddDto.getPhoneNumber())){
            return new ErrorResult("Phone Number Already In Use");
        }
        Customer customer = Customer.builder()
                .email(customerAddDto.getEmail())
                .password(customerAddDto.getPassword())
                .name(customerAddDto.getName())
                .surname(customerAddDto.getSurname())
                .phoneNumber(customerAddDto.getPhoneNumber())
                .balance(0)
                .dateCreated(LocalDateTime.now())
                .emailValidation(false)
                .phoneValidation(false)
                .build();

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
        customer.setName(customerBaseUpdateDto.getName());
        customer.setSurname(customerBaseUpdateDto.getSurname());
        customer.setPhoneNumber(customerBaseUpdateDto.getPhoneNumber());
        customerDao.save(customer);
        return new SuccessDataResult<>(customer, "Customer Updated");
    }

    //TODO: REAL CREDIT CARD PAYMENT
    @Override
    public Result depositBalance(CustomerDepositBalanceDto customerDepositBalanceDto) {
        if(!customerDao.existsById(customerDepositBalanceDto.getFkCustomerId())){
            return new ErrorResult("Customer Not Found");
        }
        Customer customer = customerDao.findById(customerDepositBalanceDto.getFkCustomerId()).get();
        int newBalance = customer.getBalance() + customerDepositBalanceDto.getDepositAmount();
        customer.setBalance(newBalance);
        customerDao.save(customer);
        return new SuccessDataResult<>(newBalance,"Customer Balance Updated");
    }
}
