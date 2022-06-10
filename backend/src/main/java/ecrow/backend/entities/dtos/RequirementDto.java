package ecrow.backend.entities.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequirementDto implements Serializable {
    private final Integer id;
    private final Integer fkTransactionId;
    private final String requirement;
}
