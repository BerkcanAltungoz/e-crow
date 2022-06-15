package ecrow.backend.entities.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class PaymentMethodAddDto implements Serializable {

    @NotNull(message = "Required")
    private final Integer fkCustomerId;

    @NotBlank(message = "Field Cannot Be Empty")
    @NotNull(message = "Required")
    private final String nameOnCard;

    @NotNull(message = "Required")
    @Pattern(regexp = "\\d{16}", message = "Invalid Card Number Format")
    private final String cardNumber;

    @NotNull(message = "Required")
    @Pattern(regexp = "\\d{3}", message = "Invalid Card Number Format")
    private final String cvc;

    @NotNull(message = "Required")
    @Pattern(regexp = "\\d{2}", message = "Invalid Expiry Date Month Format")
    private Integer expiryDateMonth;

    @NotNull(message = "Required")
    @Pattern(regexp = "\\d{2}", message = "Invalid Expiry Date Year Format")
    private Integer expiryDateYear;
}
