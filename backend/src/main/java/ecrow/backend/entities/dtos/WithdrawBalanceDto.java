package ecrow.backend.entities.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class WithdrawBalanceDto implements Serializable {
    private final Integer fkUserId;

    private final Integer fkBankInformationId;

    @NotNull(message = "Required")
    private final Integer withdrawAmount;


}
