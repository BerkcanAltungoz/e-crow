package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.BaseUser;
import ecrow.backend.entities.dtos.WithdrawBalanceDto;

import java.util.List;

public interface BaseUserService {
    DataResult<List<BaseUser>> getAll();
    DataResult<BaseUser> getById(Integer id);
    DataResult<BaseUser> getByEmail(String email);
    DataResult<BaseUser> getByPhoneNumber(String phoneNumber);
    Result withdrawBalance(WithdrawBalanceDto withdrawBalanceDto);
}
