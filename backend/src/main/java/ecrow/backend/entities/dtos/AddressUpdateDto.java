package ecrow.backend.entities.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Builder
@Data
public class AddressUpdateDto implements Serializable {
    private final Integer id;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    private final String namesurname;

    @NotNull(message = "Required")
    private final Integer fkCityId;

    @NotNull(message = "Required")
    private final Integer fkTownId;

    @NotNull(message = "Required")
    @Pattern(regexp = "\\d{5}", message = "Invalid Postal Code Format")
    private final String postalCode;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    private final String addressLine;
}
