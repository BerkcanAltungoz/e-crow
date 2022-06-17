package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.RequirementService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.RequirementDao;
import ecrow.backend.dataAccess.concretes.ItemTransactionDao;
import ecrow.backend.entities.concretes.Requirement;
import ecrow.backend.entities.dtos.RequirementAddDto;
import ecrow.backend.entities.dtos.RequirementSatisfiedUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementManager implements RequirementService {
    private final RequirementDao requirementDao;
    private final ItemTransactionDao itemTransactionDao;

    @Autowired
    public RequirementManager(RequirementDao requirementDao, ItemTransactionDao itemTransactionDao) {
        this.requirementDao = requirementDao;
        this.itemTransactionDao = itemTransactionDao;
    }

    @Override
    public DataResult<List<Requirement>> getAll() {
        return new SuccessDataResult<>(requirementDao.findAll());
    }

    @Override
    public DataResult<Requirement> getById(Integer id) {
        if(!requirementDao.existsById(id)){
            return new ErrorDataResult<>("Requirement Not Found");
        }
        return new SuccessDataResult<>(requirementDao.findById(id).get());
    }

    @Override
    public DataResult<List<Requirement>> getByFkTransactionId(Integer itemTransactionId) {
        if(!requirementDao.existsByFkItemTransactionId(itemTransactionId)){
            return new ErrorDataResult<>("Transaction Not Found");
        }
        return new SuccessDataResult<>(requirementDao.getByFkItemTransactionId(itemTransactionId));
    }

    @Override
    public Result deleteById(Integer id) {
        if(!requirementDao.existsById(id)){
            return new ErrorDataResult<>("Requirement Not Found");
        }
        requirementDao.deleteById(id);
        return new SuccessResult("Requirement Deleted");
    }

    @Override
    public Result add(RequirementAddDto requirementAddDto) {
        if(!itemTransactionDao.existsById(requirementAddDto.getFkTransactionId())){
            return new ErrorResult("Invalid Transaction Id");
        }
        Requirement requirement = Requirement.builder()
                .fkItemTransaction(itemTransactionDao.findById(requirementAddDto.getFkTransactionId()).get())
                .requirement(requirementAddDto.getRequirement())
                .build();
        requirementDao.save(requirement);
        return new SuccessResult("Requirement Added");
    }

    @Override
    public Result updateSatisfied(RequirementSatisfiedUpdateDto requirementSatisfiedUpdateDto) {
        if(!requirementDao.existsById(requirementSatisfiedUpdateDto.getId())){
            return new ErrorResult("Requirement Not Found");
        }
        Requirement requirement = requirementDao.findById(requirementSatisfiedUpdateDto.getId()).get();
        requirement.setSatisfied(requirementSatisfiedUpdateDto.getSatisfied());
        return new SuccessResult("Satisfied Updated");
    }

    @Override
    public Result updateSatisfiedTrue(RequirementSatisfiedUpdateDto requirementSatisfiedUpdateDto) {
        if(!requirementDao.existsById(requirementSatisfiedUpdateDto.getId())){
            return new ErrorDataResult<>("Requirement Not Found");
        }
        Requirement requirement = requirementDao.findById(requirementSatisfiedUpdateDto.getId()).get();
        requirement.setSatisfied(true);
        return new SuccessResult("Satisfied Updated to True");
    }
}
