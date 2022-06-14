package ecrow.backend.entities.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Data
public class TransactionAddDto implements Serializable {

    @NotNull(message = "Required")
    private final Integer fkEmployeeId;

    @NotNull(message = "Required")
    private final Integer fkBuyerId;

    @NotNull(message = "Required")
    private final Integer fkSellerId;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    private final String itemName;

    @NotNull(message = "Required")
    @PositiveOrZero(message = "Cannot Be Negative")
    private final Integer itemPrice;

    @NotNull(message = "Required")
    @PositiveOrZero(message = "Cannot Be Negative")
    private final Integer employeeFee;
}
