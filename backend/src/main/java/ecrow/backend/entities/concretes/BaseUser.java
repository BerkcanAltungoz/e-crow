package ecrow.backend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "base_user")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Required")
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])\n", message = "Invalid Email Format")
    @Column(name = "email", nullable = false, unique = true ,length = 100)
    private String email;


    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @NotNull(message = "Required")
    @Pattern(regexp = "\\d{10}", message = "Invalid Phone Number Format")
    @Column(name = "phone_number", nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @Column(name = "balance")
    private Integer balance;

}