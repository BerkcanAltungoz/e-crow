package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.TownService;
import ecrow.backend.core.utilities.results.*;
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
        if(!existsById(id).isSuccess()){
            return new ErrorDataResult<>("Town Not Found");
        }
        return new SuccessDataResult<>(townDao.findById(id).get());
    }

    @Override
    public Result existsById(Integer id) {
        return townDao.existsById(id) ? new SuccessResult() : new ErrorResult();
    }

    @Override
    public Result existsByFkCityId(Integer cityId) {
        return townDao.existsByFkCityId(cityId) ? new SuccessResult() : new ErrorResult();
    }

    @Override
    public DataResult<List<Town>> getByFkCityId(Integer cityId) {
        if(!existsByFkCityId(cityId).isSuccess()){
            return new ErrorDataResult<>("Town Not Found");
        }
        return new SuccessDataResult<>(townDao.getByFkCityId(cityId));
    }
}
