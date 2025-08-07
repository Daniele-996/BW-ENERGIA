package TeamOne.BW_ENERGIA.repositories;

import TeamOne.BW_ENERGIA.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long> {
    Optional<Comune> findByNome(String nome);
}
