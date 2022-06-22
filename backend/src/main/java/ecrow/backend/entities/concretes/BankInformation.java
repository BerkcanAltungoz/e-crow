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
@Table(name = "bank_information")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BankInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_information_id_generator")
    @SequenceGenerator(name = "bank_information_id_generator", sequenceName = "bank_information_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private BaseUser fkUser;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @NotNull(message = "Required")
    @Pattern(regexp = "TR\\d{24}", message = "Invalid IBAN Format")
    @Column(name = "iban", nullable = false, unique = true, length = 26)
    private String iban;


}