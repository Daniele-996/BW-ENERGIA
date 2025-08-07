package TeamOne.BW_ENERGIA.services;

import TeamOne.BW_ENERGIA.entities.Cliente;
import TeamOne.BW_ENERGIA.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public Map<Object, Object> findAll() {
        return clienteRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Cliente::getId,
                        cliente -> {
                            Map<String, Object> mappa = new java.util.HashMap<>();
                            mappa.put("nome", cliente.getId());
                            mappa.put("cognome", cliente.getRagioneSociale());
                            mappa.put("partita iva", cliente.getPartitaIva());
                            mappa.put("email", cliente.getEmail());
                            mappa.put("data inserimento", cliente.getDataInserimento());
                            mappa.put("data ultimo contatto", cliente.getDataUltimoContatto());
                            mappa.put("fatturato annuale", cliente.getFatturatoAnnuale());
                            mappa.put("pec", cliente.getPec());
                            mappa.put("telefono", cliente.getTelefono());
                            mappa.put("email contatto", cliente.getEmailContatto());
                            mappa.put("nome contatto", cliente.getNomeContatto());
                            mappa.put("cognome contatto", cliente.getCognomeContatto());
                            mappa.put("telefono contatto", cliente.getTelefonoContatto());
                            mappa.put("logo aziendale", cliente.getLogoAziendale());
                            return mappa;
                        }
                ));
    }

    public Page<Cliente> filterByFatturato(int min, int max, Pageable pageable) {
        return clienteRepository.findByFatturatoAnnualeBetween(min, max, pageable);
    }

    public Page<Cliente> filterByDataInserimento(LocalDate data, Pageable pageable) {
        return clienteRepository.findByDataInserimento(data, pageable);
    }

    public Page<Cliente> filterByDataUltimoContatto(LocalDate data, Pageable pageable) {
        return clienteRepository.findByDataUltimoContatto(data, pageable);
    }

    public Page<Cliente> filterByNome(String nome, Pageable pageable) {
        return clienteRepository.findByRagioneSocialeContainingIgnoreCase(nome, pageable);
    }

    public Page<Cliente> findAllFilter(Specification<Cliente> spec, Pageable pageable) {
        return clienteRepository.findAll(spec, pageable);
    }
}
