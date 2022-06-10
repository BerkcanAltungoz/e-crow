package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.CustomerService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.CustomerDao;
import ecrow.backend.entities.concretes.Customer;
import ecrow.backend.entities.dtos.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements CustomerService {
    private final CustomerDao customerDao;

    @Autowired
    public CustomerManager(CustomerDao customerDao) {
        this.customerDao = customerDao;
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
    public Result deleteById(Integer id) {
        if(!customerDao.existsById(id)){
            return new ErrorResult("Customer Not Found");
        }
        return new SuccessResult("Customer Deleted");
    }

    @Override
    public Result add(CustomerDto customerDto) {
        if(customerDao.existsByEmail(customerDto.getEmail())){
            return new ErrorResult("Email Already In Use");
        }
        else if(customerDao.existsByPhoneNumber(customerDto.getPhoneNumber())){
            return new ErrorResult("Phone Number Already In Use");
        }
        Customer customer = Customer.builder()
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
        customerDao.save(customer);
        return new SuccessResult("Customer Saved");
    }

    @Override
    public Result update(CustomerDto customerDto) {
        if(!customerDao.existsById(customerDto.getId())){
            return new ErrorResult("Customer Not Found");
        }
        Customer customer = customerDao.findById(customerDto.getId()).get();
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setName(customer.getName());
        customer.setSurname(customer.getSurname());
        customer.setPhoneNumber(customer.getPhoneNumber());
        return new SuccessResult("Customer Updated");
    }
}
