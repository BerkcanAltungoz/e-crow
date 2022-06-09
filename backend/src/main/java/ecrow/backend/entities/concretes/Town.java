package ecrow.backend.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "town")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "fk_city_id", nullable = false)
    private City city;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(name = "name", nullable = false)
    private String name;

}