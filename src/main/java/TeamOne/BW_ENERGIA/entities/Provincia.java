package TeamOne.BW_ENERGIA.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "province")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String sigla;

    private String nome;

    private String regione;
}
