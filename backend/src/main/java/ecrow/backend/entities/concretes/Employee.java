package ecrow.backend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@SuperBuilder
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

    @PositiveOrZero(message = "Cannot Be Negative")
    @Column(name = "fee")
    private Integer fee;

    @PositiveOrZero(message = "Cannot Be Negative")
    @Column(name = "expertise_fee")
    private Integer expertiseFee;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "email_validation")
    private Boolean emailValidation;

    @Column(name = "phone_validation")
    private Boolean phoneValidation;



}