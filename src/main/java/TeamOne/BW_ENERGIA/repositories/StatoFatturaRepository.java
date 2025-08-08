package TeamOne.BW_ENERGIA.repositories;

import TeamOne.BW_ENERGIA.entities.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {
}
