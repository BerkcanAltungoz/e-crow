package ecrow.backend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment_method")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_customer_id", nullable = false)
    private Customer fkCustomer;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "name_on_card", nullable = false, length = 100)
    private String nameOnCard;

    @NotNull(message = "Required")
    @Pattern(regexp = "\\d{16}", message = "Invalid Card Number Format")
    @Column(name = "card_number", nullable = false, unique = true, length = 16)
    private String cardNumber;

    @NotNull(message = "Required")
    @Pattern(regexp = "\\d{3}", message = "Invalid Card Number Format")
    @Column(name = "cvc", nullable = false, length = 3)
    private String cvc;

    @NotNull(message = "Required")
    @Pattern(regexp = "\\d{2}", message = "Invalid Expiry Date Month Format")
    @Column(name = "expiry_date_month", nullable = false, length = 2)
    private Integer expiryDateMonth;

    @NotNull(message = "Required")
    @Pattern(regexp = "\\d{2}", message = "Invalid Expiry Date Year Format")
    @Column(name = "expiry_date_year", nullable = false, length = 2)
    private Integer expiryDateYear;

}