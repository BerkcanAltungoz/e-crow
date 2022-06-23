package ecrow.backend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item_transaction")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ItemTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_transaction_id_generator")
    @SequenceGenerator(name = "item_transaction_id_generator", sequenceName = "item_transaction_id_generator", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_employee_id", nullable = false)
    private Employee fkEmployee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_buyer_id", nullable = false)
    private Customer fkBuyer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_seller_id", nullable = false)
    private Customer fkSeller;

    @NotNull(message = "Required")
    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName;

    @NotNull(message = "Required")
    @PositiveOrZero(message = "Cannot Be Negative")
    @Column(name = "item_price", nullable = false)
    private Integer itemPrice;

    @NotNull(message = "Required")
    @PositiveOrZero(message = "Cannot Be Negative")
    @Column(name = "employee_fee", nullable = false)
    private Integer employeeFee;

    @Lob
    @Column(name = "details", nullable = false)
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_status_id")
    private Status fkStatus;

    @CreatedDate
    @Column(name = "date_created")
    private LocalDateTime dateCreated;


}