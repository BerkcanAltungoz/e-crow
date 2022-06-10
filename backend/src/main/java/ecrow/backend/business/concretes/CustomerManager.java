package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.CustomerService;
import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.SuccessDataResult;
import ecrow.backend.dataAccess.concretes.CustomerDao;
import ecrow.backend.entities.concretes.Customer;
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
        return new SuccessDataResult<>(customerDao.findById(id).get());
    }
}
