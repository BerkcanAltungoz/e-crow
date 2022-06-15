package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.BankInformationService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.BankInformationDao;
import ecrow.backend.dataAccess.concretes.BaseUserDao;
import ecrow.backend.entities.concretes.BankInformation;
import ecrow.backend.entities.dtos.BankInformationAddDto;
import ecrow.backend.entities.dtos.BankInformationUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankInformationManager implements BankInformationService {
    private final BankInformationDao bankInformationDao;
    private final BaseUserDao baseUserDao;

    @Autowired
    public BankInformationManager(BankInformationDao bankInformationDao, BaseUserDao baseUserDao) {
        this.bankInformationDao = bankInformationDao;
        this.baseUserDao = baseUserDao;
    }

    @Override
    public DataResult<List<BankInformation>> getAll() {
        return new SuccessDataResult<>(bankInformationDao.findAll());
    }

    @Override
    public DataResult<BankInformation> getById(Integer id) {
        if(!bankInformationDao.existsById(id)){
            return new ErrorDataResult<>("Bank Information Not Found");
        }
        return new SuccessDataResult<>(bankInformationDao.findById(id).get()) ;
    }

    @Override
    public DataResult<BankInformation> getByFkUserId(Integer userId) {
        if(!bankInformationDao.existsByFkUserId(userId)){
            return new ErrorDataResult<>("Bank Information Not Found");
        }
        return new SuccessDataResult<>(bankInformationDao.getByFkUserId(userId));
    }

    @Override
    public Result deleteById(Integer id) {
        if(!bankInformationDao.existsById(id)){
            return new ErrorDataResult<>("Bank Information Not Found");
        }
        bankInformationDao.deleteById(id);
        return new SuccessResult("Bank Information Deleted");
    }

    @Override
    public Result add(BankInformationAddDto bankInformationAddDto) {
        if(!baseUserDao.existsById(bankInformationAddDto.getFkUserId())){
            return new ErrorResult("Invalid User Id");
        }
        else if(bankInformationDao.existsByIban(bankInformationAddDto.getIban())){
            return new ErrorResult("IBAN Already In Use");
        }
        BankInformation bankInformation = BankInformation.builder()
                .fkUser(baseUserDao.findById(bankInformationAddDto.getFkUserId()).get())
                .nickname(bankInformationAddDto.getNickname())
                .iban(bankInformationAddDto.getIban())
                .build();
        bankInformationDao.save(bankInformation);
        return new SuccessResult("Bank Information Saved");
    }

    @Override
    public Result update(BankInformationUpdateDto bankInformationUpdateDto) {
        if(!bankInformationDao.existsById(bankInformationUpdateDto.getId())){
            return new ErrorResult("Bank Information Not Found");
        }

        BankInformation bankInformation = bankInformationDao.findById(bankInformationUpdateDto.getId()).get();
        bankInformation.setNickname(bankInformationUpdateDto.getNickname());
        bankInformation.setIban(bankInformationUpdateDto.getIban());
        return new SuccessResult("Bank Information Updated");
    }
}
