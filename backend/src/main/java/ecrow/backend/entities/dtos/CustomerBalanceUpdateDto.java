package ecrow.backend.entities.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CustomerBalanceUpdateDto implements Serializable {
    private final Integer id;

    @NotNull(message = "Required")
    private final Integer balance;
}
