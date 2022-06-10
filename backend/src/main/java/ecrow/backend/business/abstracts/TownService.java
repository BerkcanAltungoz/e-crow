package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.City;
import ecrow.backend.entities.concretes.Town;

import java.util.List;

public interface TownService {
    DataResult<List<Town>> getAll();
    DataResult<Town> getById(Integer id);
    Result existsById(Integer id);
    Result existsByFkCityId(Integer cityId);
    DataResult<List<Town>> getByFkCityId(Integer cityId);
}
