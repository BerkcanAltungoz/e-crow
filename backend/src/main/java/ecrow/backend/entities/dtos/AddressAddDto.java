package ecrow.backend.entities.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class AddressAddDto implements Serializable {
    private final Integer fkCustomerId;
    private final String namesurname;
    private final Integer fkCityId;
    private final Integer fkTownId;
    private final String postalCode;
    private final String addressLine;
}