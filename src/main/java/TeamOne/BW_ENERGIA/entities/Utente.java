package TeamOne.BW_ENERGIA.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String avatar;
    @ManyToMany
    @JoinTable(name = "utenti_ruoli",
            joinColumns = @JoinColumn(name = "utenti_id"),
            inverseJoinColumns = @JoinColumn(name = "ruoli_id"))
    private List<Ruolo> ruoli = new ArrayList<>();
}
