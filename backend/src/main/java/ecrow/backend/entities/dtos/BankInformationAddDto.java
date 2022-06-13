package ecrow.backend.entities.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class BankInformationAddDto implements Serializable {
    private final Integer fkCustomerId;
    private final String nickname;
    private final String iban;
}
