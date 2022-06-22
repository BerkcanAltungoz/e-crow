package ecrow.backend.entities.concretes;

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
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_message_id_generator")
    @SequenceGenerator(name = "chat_message_id_generator", sequenceName = "chat_message_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_employee_id", nullable = false)
    private Employee fkEmployee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_customer_id", nullable = false)
    private Customer fkCustomer;

    @NotNull(message = "Required")
    @NotBlank(message = "Field Cannot Be Empty")
    @Lob
    @Column(name = "message", nullable = false)
    private String message;

}