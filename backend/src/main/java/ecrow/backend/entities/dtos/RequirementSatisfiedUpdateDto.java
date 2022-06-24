package ecrow.backend.entities.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequirementSatisfiedUpdateDto implements Serializable {
    private final Integer id;
    private final Boolean satisfied;
}
