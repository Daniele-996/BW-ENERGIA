package TeamOne.BW_ENERGIA.repositories;

import TeamOne.BW_ENERGIA.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByUsername(String username);

    Optional<Utente> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
