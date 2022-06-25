package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.BaseUserService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.BaseUserDao;
import ecrow.backend.entities.concretes.BaseUser;
import ecrow.backend.entities.concretes.Customer;
import ecrow.backend.entities.dtos.WithdrawBalanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseUserManager implements BaseUserService {
    private final BaseUserDao baseUserDao;

    @Autowired
    public BaseUserManager(BaseUserDao baseUserDao) {
        this.baseUserDao = baseUserDao;
    }

    @Override
    public DataResult<List<BaseUser>> getAll() {
        return new SuccessDataResult<>(baseUserDao.findAll());
    }

    @Override
    public DataResult<BaseUser> getById(Integer id) {
        if(!baseUserDao.existsById(id)){
            return new ErrorDataResult<>("Base User Not Found");
        }
        return new SuccessDataResult<>(baseUserDao.findById(id).get());
    }

    @Override
    public DataResult<BaseUser> getByEmail(String email) {
        if(!baseUserDao.existsByEmail(email)){
            return new ErrorDataResult<>("Base User Not Found");
        }
        return new SuccessDataResult<>(baseUserDao.getByEmail(email));
    }

    @Override
    public DataResult<BaseUser> getByPhoneNumber(String phoneNumber) {
        if(!baseUserDao.existsByPhoneNumber(phoneNumber)){
            return new ErrorDataResult<>("Base User Not Found");
        }
        return new SuccessDataResult<>(baseUserDao.getByPhoneNumber(phoneNumber));
    }

    //TODO: WITHDRAW FROM BANK ACCOUNT
    @Override
    public Result withdrawBalance(WithdrawBalanceDto withdrawBalanceDto) {
        if(!baseUserDao.existsById(withdrawBalanceDto.getFkUserId())){
            return new ErrorResult("User Not Found");
        }
        BaseUser baseUser = baseUserDao.findById(withdrawBalanceDto.getFkUserId()).get();
        int newBalance = baseUser.getBalance() - withdrawBalanceDto.getWithdrawAmount();
        baseUser.setBalance(newBalance);
        baseUserDao.save(baseUser);
        return new SuccessDataResult<>(newBalance,"Withdraw Successful");
    }
}
