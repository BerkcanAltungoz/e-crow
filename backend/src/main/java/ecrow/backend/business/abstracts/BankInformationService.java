package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.BankInformation;
import ecrow.backend.entities.dtos.BankInformationAddDto;
import ecrow.backend.entities.dtos.BankInformationUpdateDto;

import java.util.List;

public interface BankInformationService {
    DataResult<List<BankInformation>> getAll();
    DataResult<BankInformation> getById(Integer id);
    DataResult<List<BankInformation>> getByFkUserId(Integer userId);
    Result deleteById(Integer id);

    Result add(BankInformationAddDto bankInformationAddDto);
    Result update(BankInformationUpdateDto bankInformationUpdateDto);
}
