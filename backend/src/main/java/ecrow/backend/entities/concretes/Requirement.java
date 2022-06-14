package ecrow.backend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "requirement", nullable = false, length = 200)
    private String requirement;

    @Column(name = "satisfied")
    private Boolean satisfied;

}