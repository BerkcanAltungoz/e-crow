package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.PaymentMethodService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.CustomerDao;
import ecrow.backend.dataAccess.concretes.PaymentMethodDao;
import ecrow.backend.entities.concretes.PaymentMethod;
import ecrow.backend.entities.dtos.PaymentMethodAddDto;
import ecrow.backend.entities.dtos.PaymentMethodUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodManager implements PaymentMethodService {
    private final PaymentMethodDao paymentMethodDao;
    private final CustomerDao customerDao;

    @Autowired
    public PaymentMethodManager(PaymentMethodDao paymentMethodDao, CustomerDao customerDao) {
        this.paymentMethodDao = paymentMethodDao;
        this.customerDao = customerDao;
    }

    @Override
    public DataResult<List<PaymentMethod>> getAll() {
        return new SuccessDataResult<>(paymentMethodDao.findAll());
    }

    @Override
    public DataResult<PaymentMethod> getById(Integer id) {
        if(!paymentMethodDao.existsById(id)){
            return new ErrorDataResult<>("Payment Method Not Found");
        }
        return new SuccessDataResult<>(paymentMethodDao.findById(id).get());
    }

    @Override
    public DataResult<List<PaymentMethod>> getByFkCustomerId(Integer customerId) {
        if(!paymentMethodDao.existsByFkCustomerId(customerId)){
            return new ErrorDataResult<>("Payment Method Not Found");
        }
        return new SuccessDataResult<>(paymentMethodDao.getByFkCustomerId(customerId));
    }

    @Override
    public Result deleteById(Integer id) {
        if(!paymentMethodDao.existsById(id)){
            return new ErrorDataResult<>("Payment Method Not Found");
        }
        paymentMethodDao.deleteById(id);
        return new SuccessResult("Payment Method Deleted");
    }

    @Override
    public Result add(PaymentMethodAddDto paymentMethodAddDto) {
        if(!customerDao.existsById(paymentMethodAddDto.getFkCustomerId())){
            return new ErrorResult("Invalid Customer Id");
        }
        else if(paymentMethodDao.existsByCardNumber(paymentMethodAddDto.getCardNumber())){
            return new ErrorResult("Card Number Already In Use");
        }

        PaymentMethod paymentMethod = PaymentMethod.builder()
                .fkCustomer(customerDao.findById(paymentMethodAddDto.getFkCustomerId()).get())
                .nameOnCard(paymentMethodAddDto.getNameOnCard())
                .cardNumber(paymentMethodAddDto.getCardNumber())
                .cvc(paymentMethodAddDto.getCvc())
                .expiryDateMonth(paymentMethodAddDto.getExpiryDateMonth())
                .expiryDateYear(paymentMethodAddDto.getExpiryDateYear())
                .build();
        paymentMethodDao.save(paymentMethod);
        return new SuccessResult("Payment Method Saved");
    }

    @Override
    public Result update(PaymentMethodUpdateDto paymentMethodUpdateDto) {
        if(!paymentMethodDao.existsById(paymentMethodUpdateDto.getId())){
            return new ErrorDataResult<>("Payment Method Not Found");
        }
        PaymentMethod paymentMethod = paymentMethodDao.findById(paymentMethodUpdateDto.getId()).get();
        paymentMethod.setNameOnCard(paymentMethodUpdateDto.getNameOnCard());
        paymentMethod.setCardNumber(paymentMethodUpdateDto.getCardNumber());
        paymentMethod.setCvc(paymentMethodUpdateDto.getCvc());
        paymentMethod.setExpiryDateMonth(paymentMethodUpdateDto.getExpiryDateMonth());
        paymentMethod.setExpiryDateYear(paymentMethodUpdateDto.getExpiryDateYear());
        return new SuccessResult("Payment Method Saved");
    }
}
