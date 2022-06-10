package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.AddressService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.AddressDao;
import ecrow.backend.dataAccess.concretes.CityDao;
import ecrow.backend.dataAccess.concretes.CustomerDao;
import ecrow.backend.dataAccess.concretes.TownDao;
import ecrow.backend.entities.concretes.Address;
import ecrow.backend.entities.dtos.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressManager implements AddressService {
    private final AddressDao addressDao;
    private final CustomerDao customerDao;
    private final TownDao townDao;
    private final CityDao cityDao;

    @Autowired
    public AddressManager(AddressDao addressDao, CustomerDao customerDao, TownDao townDao, CityDao cityDao) {
        this.addressDao = addressDao;
        this.customerDao = customerDao;
        this.townDao = townDao;
        this.cityDao = cityDao;
    }


    @Override
    public DataResult<List<Address>> getAll() {
        return new SuccessDataResult<>(addressDao.findAll());
    }

    @Override
    public DataResult<Address> getById(Integer id) {
        if(!addressDao.existsById(id)){
            return new ErrorDataResult<>("Address Not Found");
        }
        return new SuccessDataResult<>(addressDao.findById(id).get());
    }

    @Override
    public Result deleteById(Integer id) {
        if(!addressDao.existsById(id)){
            return new ErrorDataResult<>("Address Not Found");
        }
        addressDao.deleteById(id);
        return new SuccessResult("Address Deleted");
    }


    @Override
    public DataResult<List<Address>> getByFkCustomerId(Integer customerId) {
        if(!addressDao.existsByFkCustomerId(customerId)){
            return new ErrorDataResult<>("Address Not Found");
        }
        return new SuccessDataResult<>(addressDao.getByFkCustomerId(customerId));
    }

    @Override
    public Result add(AddressDto addressDto) {
        if(!addressDao.existsByFkCustomerId(addressDto.getFkCustomerId())){
            return new ErrorResult("Invalid Customer Id");
        }
        else if(!addressDao.existsByFkCityId(addressDto.getFkCityId())){
            return new ErrorResult("Invalid City Id");
        }
        else if(!addressDao.existsByFkTownId(addressDto.getFkTownId())){
            return new ErrorResult("Invalid Town Id");
        }
        Address address = Address.builder()
                .fkCustomer(customerDao.findById(addressDto.getFkCustomerId()).get())
                .namesurname(addressDto.getNamesurname())
                .fkCity(cityDao.findById(addressDto.getFkCityId()).get())
                .fkTown(townDao.findById(addressDto.getFkTownId()).get())
                .postalCode(addressDto.getPostalCode())
                .addressLine(addressDto.getAddressLine())
                .build();

        addressDao.save(address);
        return new SuccessResult("Address Saved");
    }

    @Override
    public Result update(AddressDto addressDto) {
        if(!addressDao.existsById(addressDto.getId())){
            new ErrorResult("Address Not Found");
        }
        Address address = addressDao.findById(addressDto.getId()).get();
        address.setNamesurname(addressDto.getNamesurname());
        address.setPostalCode(addressDto.getPostalCode());
        address.setAddressLine(addressDto.getAddressLine());
        return new SuccessResult("Address Updated");
    }
}
