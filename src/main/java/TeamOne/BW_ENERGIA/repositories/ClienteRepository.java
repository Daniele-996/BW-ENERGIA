package TeamOne.BW_ENERGIA.repositories;

import TeamOne.BW_ENERGIA.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
    // Filtra per range di fatturato
    Page<Cliente> findByFatturatoAnnualeBetween(int min, int max, Pageable pageable);

    // Filtra per data inserimento
    Page<Cliente> findByDataInserimento(LocalDate data, Pageable pageable);

    // Filtra per data ultimo contatto
    Page<Cliente> findByDataUltimoContatto(LocalDate data, Pageable pageable);

    // Filtra per parte del nome (ragione sociale)
    Page<Cliente> findByRagioneSocialeContainingIgnoreCase(String nome, Pageable pageable);
}
