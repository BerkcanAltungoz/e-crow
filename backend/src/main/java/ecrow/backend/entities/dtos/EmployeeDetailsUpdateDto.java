package ecrow.backend.entities.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Data
public class EmployeeDetailsUpdateDto implements Serializable {
    private final Integer id;

    @NotNull(message = "Required")
    private final Integer fkCityId;

    @NotNull(message = "Required")
    private final Integer fkTownId;

    private final String expertise;
    @PositiveOrZero(message = "Cannot Be Negative")
    private final Integer fee;
    @PositiveOrZero(message = "Cannot Be Negative")
    private final Integer expertiseFee;
    private final Boolean available;
    private final String description;
}
