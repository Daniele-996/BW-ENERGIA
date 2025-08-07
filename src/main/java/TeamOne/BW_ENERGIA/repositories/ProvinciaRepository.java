package TeamOne.BW_ENERGIA.repositories;

import TeamOne.BW_ENERGIA.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    Optional<Provincia> findBySigla(String sigla);

    Optional<Provincia> findByNome(String nome);

    Optional<Provincia> findByNomeIgnoreCase(String nome);
}
