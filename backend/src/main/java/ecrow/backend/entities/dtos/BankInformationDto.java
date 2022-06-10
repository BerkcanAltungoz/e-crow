package ecrow.backend.entities.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class BankInformationDto implements Serializable {
    private final Integer id;
    private final Integer fkCustomerId;
    private final String nickname;
    private final String iban;
}
