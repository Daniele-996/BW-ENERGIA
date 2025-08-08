package TeamOne.BW_ENERGIA.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String username;

    private String email;

    private String password;

    private String nome;

    private String cognome;

    private String avatar = "https://yourdomain.com/images/avatar-placeholder.png";

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utenti_ruoli",
            joinColumns = @JoinColumn(name = "utenti_id"),
            inverseJoinColumns = @JoinColumn(name = "ruoli_id"))
    private List<Ruolo> ruoli = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ruoli.stream()
                .map(ruolo -> new SimpleGrantedAuthority(ruolo.getRuolo()))
                .collect(Collectors.toList());
    }
}
