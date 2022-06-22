package ecrow.backend.entities.concretes;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "asd")
public class Asd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asdad", nullable = false)
    private Integer id;

    @Column(name = "asda")
    private Long asda;

}