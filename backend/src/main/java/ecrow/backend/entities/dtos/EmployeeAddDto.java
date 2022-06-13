package ecrow.backend.entities.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeAddDto implements Serializable {
    private final String email;
    private final String password;
    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final Integer fkCityId;
    private final Integer fkTownId;
}
