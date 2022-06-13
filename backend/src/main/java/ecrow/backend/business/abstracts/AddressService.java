package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.Address;
import ecrow.backend.entities.dtos.AddressDto;
import ecrow.backend.entities.dtos.AddressUpdateDto;

import java.util.List;

public interface AddressService {
    DataResult<List<Address>> getAll();
    DataResult<Address> getById(Integer id);
    DataResult<List<Address>> getByFkCustomerId(Integer customerId);
    Result deleteById(Integer id);


    Result add(AddressDto addressDto);
    Result update(AddressUpdateDto addressUpdateDto);

}
