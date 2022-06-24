package ecrow.backend.entities.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RequirementAddDto implements Serializable {
    @NotNull(message = "Required")
    private final Integer fkTransactionId;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    private final String requirement;
}
