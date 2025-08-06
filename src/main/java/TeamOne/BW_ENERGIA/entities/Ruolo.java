package TeamOne.BW_ENERGIA.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ruoli")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String ruolo;

    @ManyToMany(mappedBy = "ruoli")
    private List<Utente> utenti = new ArrayList<>();

    public Ruolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
