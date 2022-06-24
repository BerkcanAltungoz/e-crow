package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.entities.concretes.Status;

import java.util.List;

public interface StatusService {
    DataResult<List<Status>> getAll();
    DataResult<Status> getById(Integer id);
    DataResult<Status> getByName(String name);
}
