package TeamOne.BW_ENERGIA.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nome;

    @NotBlank
    private String cognome;

    @NotBlank
    private String avatar;

    @NotBlank
    @ManyToMany
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
