package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.StatusService;
import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.ErrorDataResult;
import ecrow.backend.core.utilities.results.SuccessDataResult;
import ecrow.backend.dataAccess.concretes.StatusDao;
import ecrow.backend.entities.concretes.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusManager implements StatusService {
    private final StatusDao statusDao;

    @Autowired
    public StatusManager(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @Override
    public DataResult<List<Status>> getAll() {
        return new SuccessDataResult<>(statusDao.findAll());
    }

    @Override
    public DataResult<Status> getById(Integer id) {
        if(!statusDao.existsById(id)){
            return new ErrorDataResult<>("Status Not Found");
        }
        return new SuccessDataResult<>(statusDao.findById(id).get());
    }

    @Override
    public DataResult<Status> getByName(String name) {
        if(!statusDao.existsByName(name)){
            return new ErrorDataResult<>("Status Not Found");
        }
        return new SuccessDataResult<>(statusDao.getByName(name));
    }
}
