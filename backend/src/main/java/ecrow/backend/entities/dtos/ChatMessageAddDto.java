package ecrow.backend.entities.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ChatMessageAddDto implements Serializable {
    private final Integer id;
    private final Integer fkEmployeeId;
    private final Integer fkCustomerId;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    private final String message;
}
