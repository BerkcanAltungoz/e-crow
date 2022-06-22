package ecrow.backend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "city")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_generator")
    @SequenceGenerator(name = "city_id_generator", sequenceName = "city_id_generator", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;


}
