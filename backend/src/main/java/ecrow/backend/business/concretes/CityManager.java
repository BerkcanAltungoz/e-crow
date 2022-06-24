package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.CityService;
import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.ErrorDataResult;
import ecrow.backend.core.utilities.results.SuccessDataResult;
import ecrow.backend.dataAccess.concretes.CityDao;
import ecrow.backend.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {
    private final CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<>(cityDao.findAll());
    }

    @Override
    public DataResult<City> getById(Integer id) {
        if(!cityDao.existsById(id)){
            return new ErrorDataResult<>("City Not Found");
        }
        return new SuccessDataResult<>(cityDao.findById(id).get());
    }

    @Override
    public DataResult<City> getByName(String name) {
        if(!cityDao.existsByName(name)){
            return new ErrorDataResult<>("City Not Found");
        }
        return new SuccessDataResult<>(cityDao.getByName(name));
    }
}
