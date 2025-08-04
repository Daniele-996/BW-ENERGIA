package TeamOne.BW_ENERGIA.repositories;

import TeamOne.BW_ENERGIA.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndirizziRepository extends JpaRepository<Indirizzo,Long> {
}
