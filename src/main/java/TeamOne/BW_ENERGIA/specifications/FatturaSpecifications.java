package TeamOne.BW_ENERGIA.specifications;

import TeamOne.BW_ENERGIA.entities.Fattura;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FatturaSpecifications {

    public static Specification<Fattura> clienteIdEquals(Long clienteId) {
        return (root, query, cb) -> cb.equal(root.get("cliente").get("id"), clienteId);
    }

    public static Specification<Fattura> statoFatturaEquals(String stato) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("statoFattura")), stato.toLowerCase());
    }

    public static Specification<Fattura> dataEquals(LocalDate data) {
        return (root, query, cb) -> cb.equal(root.get("data"), data);
    }

    public static Specification<Fattura> annoEquals(Integer anno) {
        return (root, query, cb) -> cb.equal(cb.function("YEAR", Integer.class, root.get("data")), anno);
    }

    public static Specification<Fattura> importoBetween(Integer min, Integer max) {
        return (root, query, cb) -> cb.between(root.get("importo"), min, max);
    }
}

