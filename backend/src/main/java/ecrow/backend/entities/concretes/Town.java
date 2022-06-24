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
@Table(name = "town")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "town_id_generator")
    @SequenceGenerator(name = "town_id_generator", sequenceName = "town_id_generator", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_city_id", nullable = false)
    private City fkCity;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

}