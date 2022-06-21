package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.Requirement;
import ecrow.backend.entities.dtos.RequirementAddDto;
import ecrow.backend.entities.dtos.RequirementSatisfiedUpdateDto;

import java.util.List;

public interface RequirementService {
    DataResult<List<Requirement>> getAll();
    DataResult<Requirement> getById(Integer id);
    DataResult<List<Requirement>> getByFkTransactionId(Integer itemTransactionId);
    Result deleteById(Integer id);

    Result add(RequirementAddDto requirementAddDto);
    Result updateSatisfied(RequirementSatisfiedUpdateDto requirementSatisfiedUpdateDto);
    Result updateSatisfiedTrue(Integer id);

}
