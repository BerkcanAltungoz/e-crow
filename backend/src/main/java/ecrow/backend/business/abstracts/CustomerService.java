package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.Customer;

import java.util.List;

public interface CustomerService {
    DataResult<List<Customer>> getAll();
    Result existsById(Integer id);
    DataResult<Customer> getById(Integer id);
}
