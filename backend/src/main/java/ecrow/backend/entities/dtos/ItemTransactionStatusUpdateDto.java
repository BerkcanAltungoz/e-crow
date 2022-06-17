package ecrow.backend.entities.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemTransactionStatusUpdateDto implements Serializable {
    private final Integer id;
    private final Integer fkStatusId;
}
