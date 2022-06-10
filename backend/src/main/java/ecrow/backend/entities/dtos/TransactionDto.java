package ecrow.backend.entities.dtos;

import lombok.Data;

import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Data
public class TransactionDto implements Serializable {
    private final Integer id;
    private final Integer fkEmployeeId;
    private final Integer fkBuyerId;
    private final Integer fkSellerId;
    private final String itemName;
    @PositiveOrZero
    private final Integer itemPrice;
    private final Integer employeeFee;
}
