package ecrow.backend.entities.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class BankInformationUpdateDto implements Serializable {
    @NotNull(message = "Required")
    private final Integer id;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    private final String nickname;

    @NotNull(message = "Required")
    @Pattern(regexp = "TR\\d{24}", message = "Invalid IBAN Format")
    private final String iban;
}
