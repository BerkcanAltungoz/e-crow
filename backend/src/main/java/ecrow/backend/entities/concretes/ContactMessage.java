package ecrow.backend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Email
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "subject", nullable = false, length = 100)
    private String subject;

    @Lob
    @Column(name = "message", nullable = false)
    private String message;

}