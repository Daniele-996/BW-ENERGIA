package TeamOne.BW_ENERGIA.repositories;

import TeamOne.BW_ENERGIA.entities.Cliente;
import TeamOne.BW_ENERGIA.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {
    boolean existsById(Long id);

    Optional<Fattura> findByCliente(Cliente cliente);
}
