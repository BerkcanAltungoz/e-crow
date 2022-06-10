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
        if(!existsById(id).isSuccess()){
            return new ErrorDataResult<>("Address Not Found");
        }
        return new SuccessDataResult<>(addressDao.findById(id).get());
    }

    @Override
    public Result deleteById(Integer id) {
        if(!existsById(id).isSuccess()){
            return new ErrorDataResult<>("Address Not Found");
        }
        addressDao.deleteById(id);
        return new SuccessResult();
    }

    @Override
    public Result existsByFkCustomerId(Integer customerId) {
        return addressDao.existsByFkCustomerId(customerId) ? new SuccessResult() : new ErrorResult();
    }

    @Override
    public Result existsById(Integer id) {
        return addressDao.existsById(id) ? new SuccessResult() : new ErrorResult();
    }

    @Override
    public DataResult<List<Address>> getByFkCustomerId(Integer customerId) {
        if(!existsByFkCustomerId(customerId).isSuccess()){
            return new ErrorDataResult<>("Address Not Found");
        }
        return new SuccessDataResult<>(addressDao.getByFkCustomerId(customerId));
    }

    @Override
    public Result add(AddressDto addressDto) {
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
        if(!existsById(addressDto.getId()).isSuccess()){
            new ErrorResult("Address Not Found");
        }
        Address address = addressDao.findById(addressDto.getId()).get();
        address.setNamesurname(addressDto.getNamesurname());
        address.setPostalCode(addressDto.getPostalCode());
        address.setAddressLine(addressDto.getAddressLine());
        return new SuccessResult("Address Updated");
    }
}
