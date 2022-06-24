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
@Table(name = "address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_generator")
    @SequenceGenerator(name = "address_id_generator", sequenceName = "address_id_generator", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_customer_id", nullable = false)
    private Customer fkCustomer;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "namesurname", nullable = false, length = 100)
    private String namesurname;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_city_id", nullable = false)
    private City fkCity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_town_id", nullable = false)
    private Town fkTown;

    @NotNull(message = "Required")
    @Pattern(regexp = "\\d{5}", message = "Invalid Postal Code Format")
    @Column(name = "postal_code", nullable = false, length = 5)
    private String postalCode;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "address_line", nullable = false, length = 1000)
    private String addressLine;

}