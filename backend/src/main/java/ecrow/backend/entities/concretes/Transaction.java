package ecrow.backend.entities.concretes;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.time.OffsetTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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

    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName;

    @PositiveOrZero
    @Column(name = "item_price", nullable = false)
    private Integer itemPrice;

    @Column(name = "employee_fee", nullable = false)
    private Integer employeeFee;

    @Lob
    @Column(name = "details")
    private String details;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_status_id", nullable = false)
    private Status fkStatus;

    @CreatedDate
    @Column(name = "date_created")
    private OffsetTime dateCreated;

}