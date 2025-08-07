package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Cliente;
import TeamOne.BW_ENERGIA.services.ClienteService;
import TeamOne.BW_ENERGIA.specifications.ClienteSpecifications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    // Paginazione
    @GetMapping
    public Page<Cliente> getAll(Pageable pageable) {
        return clienteService.findAll(pageable);
    }

    // Dettaglio cliente
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Aggiungi cliente
    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    // Modifica cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.findById(id)
                .map(existing -> {
                    BeanUtils.copyProperties(cliente, existing, "id", "fatture");
                    return ResponseEntity.ok(clienteService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Elimina cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (clienteService.findById(id).isPresent()) {
            clienteService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Filtri per ricerca
    //GET /api/clienti?sort=ragioneSociale,asc
    //GET /api/clienti?sort=fatturatoAnnuale,desc
    //GET /api/clienti?sort=dataInserimento,asc
    //GET /api/clienti?sort=indirizzoLegale.provincia,asc
    @GetMapping("/search")
    public Page<Cliente> searchClienti(
            @RequestParam(required = false) Integer fatturatoMin,
            @RequestParam(required = false) Integer fatturatoMax,
            @RequestParam(required = false) String dataInserimento,
            @RequestParam(required = false) String dataUltimoContatto,
            @RequestParam(required = false) String nome,
            Pageable pageable
    ) {
        Specification<Cliente> spec = null;

        if (fatturatoMin != null && fatturatoMax != null) {
            spec = ClienteSpecifications.fatturatoBetween(fatturatoMin, fatturatoMax);
        }
        if (dataInserimento != null) {
            LocalDate data = LocalDate.parse(dataInserimento);
            spec = (spec == null ? ClienteSpecifications.dataInserimentoEquals(data) : spec.and(ClienteSpecifications.dataInserimentoEquals(data)));
        }
        if (dataUltimoContatto != null) {
            LocalDate data = LocalDate.parse(dataUltimoContatto);
            spec = (spec == null ? ClienteSpecifications.dataUltimoContattoEquals(data) : spec.and(ClienteSpecifications.dataUltimoContattoEquals(data)));
        }
        if (nome != null) {
            spec = (spec == null ? ClienteSpecifications.nomeContains(nome) : spec.and(ClienteSpecifications.nomeContains(nome)));
        }

        return clienteService.findAllFilter(spec, pageable);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente patchCliente(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return clienteService.findById(id)
                .map(cliente -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "ragioneSociale" -> cliente.setRagioneSociale((String) value);
                            case "partitaIva" -> cliente.setPartitaIva((Integer) value);
                            case "email" -> cliente.setEmail((String) value);
                            case "dataInserimento" -> cliente.setDataInserimento(LocalDate.parse((String) value));
                            case "dataUltimoContatto" -> cliente.setDataUltimoContatto(LocalDate.parse((String) value));
                            case "fatturatoAnnuale" -> cliente.setFatturatoAnnuale((Integer) value);
                            case "pec" -> cliente.setPec((String) value);
                            case "telefono" -> cliente.setTelefono((Integer) value);
                            case "emailContatto" -> cliente.setEmailContatto((String) value);
                            case "nomeContatto" -> cliente.setNomeContatto((String) value);
                            case "cognomeContatto" -> cliente.setCognomeContatto((String) value);
                            case "telefonoContatto" -> cliente.setTelefonoContatto((Integer) value);
                            case "logoAziendale" -> cliente.setLogoAziendale((String) value);
                        }
                    });
                    return clienteService.save(cliente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente non trovato"));
    }
}
