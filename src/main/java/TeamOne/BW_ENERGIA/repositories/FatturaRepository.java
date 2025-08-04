package TeamOne.BW_ENERGIA.repositories;

import TeamOne.BW_ENERGIA.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {
}
