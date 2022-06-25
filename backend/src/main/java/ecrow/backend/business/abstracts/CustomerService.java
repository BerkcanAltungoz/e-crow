package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.Customer;
import ecrow.backend.entities.dtos.CustomerAddDto;
import ecrow.backend.entities.dtos.CustomerDepositBalanceDto;
import ecrow.backend.entities.dtos.CustomerBaseUpdateDto;
import ecrow.backend.entities.dtos.SignInDto;

import java.util.List;

public interface CustomerService {
    DataResult<List<Customer>> getAll();
    DataResult<Customer> getById(Integer id);
    DataResult<Customer> getByEmail(String email);
    DataResult<Customer> getByPhoneNumber(String phoneNumber);
    DataResult<Customer> signIn(SignInDto signInDto);
    Result deleteById(Integer id);

    Result add(CustomerAddDto customerAddDto);
    Result updateBase(CustomerBaseUpdateDto customerBaseUpdateDto);
    Result depositBalance(CustomerDepositBalanceDto customerDepositBalanceDto);
}
