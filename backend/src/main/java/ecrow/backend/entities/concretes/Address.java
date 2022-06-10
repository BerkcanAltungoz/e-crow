package ecrow.backend.entities.concretes;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_customer_id", nullable = false)
    private Customer fkCustomer;

    @Column(name = "namesurname", nullable = false, length = 100)
    private String namesurname;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_city_id", nullable = false)
    private City fkCity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_town_id", nullable = false)
    private Town fkTown;

    @Column(name = "postal_code", nullable = false, length = 5)
    private String postalCode;

    @Lob
    @Column(name = "address_line", nullable = false)
    private String addressLine;

}