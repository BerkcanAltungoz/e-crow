package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.TownService;
import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.ErrorDataResult;
import ecrow.backend.core.utilities.results.SuccessDataResult;
import ecrow.backend.dataAccess.concretes.TownDao;
import ecrow.backend.entities.concretes.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownManager implements TownService {
    private final TownDao townDao;

    @Autowired
    public TownManager(TownDao townDao) {
        this.townDao = townDao;
    }

    @Override
    public DataResult<List<Town>> getAll() {
        return new SuccessDataResult<>(townDao.findAll());
    }

    @Override
    public DataResult<Town> getById(Integer id) {
        if(!townDao.existsById(id)){
            return new ErrorDataResult<>("Town Not Found");
        }
        return new SuccessDataResult<>(townDao.findById(id).get());
    }

    @Override
    public DataResult<List<Town>> getByFkCityId(Integer cityId) {
        if(!townDao.existsByFkCityId(cityId)){
            return new ErrorDataResult<>("Town Not Found");
        }
        return new SuccessDataResult<>(townDao.getByFkCityId(cityId));
    }
}
