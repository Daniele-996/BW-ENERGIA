package TeamOne.BW_ENERGIA.specifications;

import TeamOne.BW_ENERGIA.entities.Cliente;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ClienteSpecifications {

    public static Specification<Cliente> fatturatoBetween(Integer min, Integer max) {
        return (root, query, cb) -> cb.between(root.get("fatturatoAnnuale"), min, max);
    }

    public static Specification<Cliente> dataInserimentoEquals(LocalDate data) {
        return (root, query, cb) -> cb.equal(root.get("dataInserimento"), data);
    }

    public static Specification<Cliente> dataUltimoContattoEquals(LocalDate data) {
        return (root, query, cb) -> cb.equal(root.get("dataUltimoContatto"), data);
    }

    public static Specification<Cliente> nomeContains(String nome) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("ragioneSociale")), "%" + nome.toLowerCase() + "%");
    }
}

