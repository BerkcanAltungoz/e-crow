package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.Customer;
import ecrow.backend.entities.dtos.CustomerDto;

import java.util.List;

public interface CustomerService {
    DataResult<List<Customer>> getAll();
    DataResult<Customer> getById(Integer id);
    DataResult<Customer> getByEmail(String email);
    DataResult<Customer> getByPhoneNumber(String phoneNumber);
    Result deleteById(Integer id);

    Result add(CustomerDto customerDto);
    Result update(CustomerDto customerDto);
}
