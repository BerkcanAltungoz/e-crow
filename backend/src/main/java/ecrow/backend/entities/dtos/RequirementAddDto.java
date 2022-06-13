package ecrow.backend.entities.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequirementAddDto implements Serializable {
    private final Integer fkTransactionId;
    private final String requirement;
}
