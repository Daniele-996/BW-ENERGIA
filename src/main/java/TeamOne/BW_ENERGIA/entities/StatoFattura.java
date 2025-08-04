package TeamOne.BW_ENERGIA.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stati_fatture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatoFattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String stato;
}
