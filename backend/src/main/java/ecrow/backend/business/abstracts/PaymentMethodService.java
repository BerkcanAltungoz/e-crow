package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.PaymentMethod;
import ecrow.backend.entities.dtos.PaymentMethodAddDto;
import ecrow.backend.entities.dtos.PaymentMethodUpdateDto;

import java.util.List;

public interface PaymentMethodService {
    DataResult<List<PaymentMethod>> getAll();
    DataResult<PaymentMethod> getById(Integer id);
    DataResult<List<PaymentMethod>> getByFkCustomerId(Integer customerId);
    Result deleteById(Integer id);

    Result add(PaymentMethodAddDto paymentMethodAddDto);
    Result update(PaymentMethodUpdateDto paymentMethodUpdateDto);
}
