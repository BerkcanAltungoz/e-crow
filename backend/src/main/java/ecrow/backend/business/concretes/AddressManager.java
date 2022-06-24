package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.AddressService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.AddressDao;
import ecrow.backend.dataAccess.concretes.CityDao;
import ecrow.backend.dataAccess.concretes.CustomerDao;
import ecrow.backend.dataAccess.concretes.TownDao;
import ecrow.backend.entities.concretes.Address;
import ecrow.backend.entities.dtos.AddressAddDto;
import ecrow.backend.entities.dtos.AddressUpdateDto;
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
            return new ErrorResult("Address Not Found");
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
    public Result add(AddressAddDto addressAddDto) {
        if(!customerDao.existsById(addressAddDto.getFkCustomerId())){
            return new ErrorResult("Invalid Customer Id");
        }
        else if(!cityDao.existsById(addressAddDto.getFkCityId())){
            return new ErrorResult("Invalid City Id");
        }
        else if(!townDao.existsById(addressAddDto.getFkTownId())){
            return new ErrorResult("Invalid Town Id");
        }
        Address address = Address.builder()
                .fkCustomer(customerDao.findById(addressAddDto.getFkCustomerId()).get())
                .namesurname(addressAddDto.getNamesurname())
                .fkCity(cityDao.findById(addressAddDto.getFkCityId()).get())
                .fkTown(townDao.findById(addressAddDto.getFkTownId()).get())
                .postalCode(addressAddDto.getPostalCode())
                .addressLine(addressAddDto.getAddressLine())
                .build();

        addressDao.save(address);
        return new SuccessResult("Address Saved");
    }

    @Override
    public Result update(AddressUpdateDto addressUpdateDto) {
        if(!addressDao.existsById(addressUpdateDto.getId())){
            new ErrorResult("Address Not Found");
        }
        else if(!addressDao.existsByFkCityId(addressUpdateDto.getFkCityId())){
            return new ErrorResult("Invalid City Id");
        }
        else if(!addressDao.existsByFkTownId(addressUpdateDto.getFkTownId())){
            return new ErrorResult("Invalid Town Id");
        }
        Address address = addressDao.findById(addressUpdateDto.getId()).get();
        address.setNamesurname(addressUpdateDto.getNamesurname());
        address.setFkCity(cityDao.findById(addressUpdateDto.getFkCityId()).get());
        address.setFkTown(townDao.findById(addressUpdateDto.getFkTownId()).get());
        address.setPostalCode(addressUpdateDto.getPostalCode());
        address.setAddressLine(addressUpdateDto.getAddressLine());
        addressDao.save(address);
        return new SuccessResult("Address Updated");
    }
}
