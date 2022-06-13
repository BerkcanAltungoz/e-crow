package ecrow.backend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee extends BaseUser{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_city_id", nullable = false)
    private City fkCity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_town_id", nullable = false)
    private Town fkTown;

    @Column(name = "expertise", length = 200)
    private String expertise;

    @Column(name = "fee")
    private Integer fee;

    @Column(name = "expertise_fee")
    private Integer expertiseFee;

    @Column(name = "available")
    private Boolean available;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "email_validation")
    private Boolean emailValidation;

    @Column(name = "phone_validation")
    private Boolean phoneValidation;

    @Column(name = "date_created")
    private OffsetTime dateCreated;

}