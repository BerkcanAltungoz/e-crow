package ecrow.backend.entities.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PaymentMethodAddDto implements Serializable {
    private final Integer fkCustomerId;
    private final String nameOnCard;
    private final String cardNumber;
    private final LocalDate expiryDate;
    private final String cvc;
}
