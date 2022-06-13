package ecrow.backend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private BaseUser fkUser;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "iban", nullable = false, length = 26)
    private String iban;

    @Column(name = "fk_customer_id", nullable = false)
    private Integer fkCustomerId;

}