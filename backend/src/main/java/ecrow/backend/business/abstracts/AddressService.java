package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.dtos.AddressAddDto;
import ecrow.backend.entities.dtos.AddressUpdateDto;

import java.util.List;

public interface AddressService {
    DataResult<List<Address>> getAll();
    DataResult<Address> getById(Integer id);
    DataResult<List<Address>> getByFkCustomerId(Integer customerId);
    Result deleteById(Integer id);


    Result add(AddressAddDto addressAddDto);
    Result update(AddressUpdateDto addressUpdateDto);

}
