package ecrow.backend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contact_message")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_message_id_generator")
    @SequenceGenerator(name = "contact_message_id_generator", sequenceName = "contact_message_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])\n", message = "Invalid Email Format")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "subject", nullable = false, length = 100)
    private String subject;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Lob
    @Column(name = "message", nullable = false)
    private String message;

}