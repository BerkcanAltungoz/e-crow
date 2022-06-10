package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.City;

import java.util.List;

public interface CityService {
    DataResult<List<City>> getAll();
    Result existsById(Integer id);
    Result existsByName(String name);
    DataResult<City> getById(Integer id);
    DataResult<City> getByName(String name);

}
