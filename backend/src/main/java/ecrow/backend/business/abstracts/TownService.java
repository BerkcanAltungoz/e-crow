package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.entities.concretes.Town;

import java.util.List;

public interface TownService {
    DataResult<List<Town>> getAll();
    DataResult<Town> getById(Integer id);
    DataResult<List<Town>> getByFkCityId(Integer cityId);
}
