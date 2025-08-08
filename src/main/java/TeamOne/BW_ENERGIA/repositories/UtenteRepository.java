package TeamOne.BW_ENERGIA.repositories;

import TeamOne.BW_ENERGIA.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByUsername(String username);

    Optional<Utente> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
