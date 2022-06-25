package ecrow.backend.entities.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CustomerDepositBalanceDto implements Serializable {
    private final Integer fkCustomerId;

    private final Integer fkPaymentMethodId;

    @NotNull(message = "Required")
    private final Integer depositAmount;


}
