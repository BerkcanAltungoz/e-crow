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
@Table(name = "requirement")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_transaction_id", nullable = false)
    private Transaction fkTransaction;

    @Column(name = "requirement", nullable = false, length = 200)
    private String requirement;

    @Column(name = "satisfied")
    private Boolean satisfied;

}