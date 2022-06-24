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
@Table(name = "status")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_id_generator")
    @SequenceGenerator(name = "status_id_generator", sequenceName = "status_id_generator", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

}