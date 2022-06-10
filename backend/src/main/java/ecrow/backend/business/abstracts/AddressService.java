package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.Address;
import ecrow.backend.entities.dtos.AddressDto;

import java.util.List;

public interface AddressService {
    DataResult<List<Address>> getAll();
    DataResult<Address> getById(Integer id);
    Result deleteById(Integer id);
    Result existsById(Integer id);
    DataResult<List<Address>> getByFkCustomerId(Integer customerId);

    Result add(AddressDto addressDto);
    Result update(AddressDto addressDto);

}
