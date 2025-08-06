package TeamOne.BW_ENERGIA.repositories;

import TeamOne.BW_ENERGIA.entities.Cliente;
import TeamOne.BW_ENERGIA.entities.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {
    boolean existsById(Long id);

    Optional<Fattura> findByCliente(Cliente cliente);

    Page<Fattura> findByClienteId(Long clienteId, Pageable pageable);

    Page<Fattura> findByStatoFatturaStato(String stato, Pageable pageable);

    Page<Fattura> findByData(LocalDate data, Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE YEAR(f.data) = :anno")
    Page<Fattura> findByAnno(@Param("anno") int anno, Pageable pageable);

    Page<Fattura> findByImportoBetween(int min, int max, Pageable pageable);
}
