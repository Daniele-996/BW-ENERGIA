package TeamOne.BW_ENERGIA.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "indirizzi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String via;
    private int civico;
    private String localita;
    private Comune comune;
}
