package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Cliente;
import TeamOne.BW_ENERGIA.services.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
//    GET /api/clienti?sort=ragioneSociale,asc
//    GET /api/clienti?sort=fatturatoAnnuale,desc
//    GET /api/clienti?sort=dataInserimento,asc
//    GET /api/clienti?sort=indirizzoLegale.provincia,asc
    @GetMapping("/search")
    public Page<Cliente> searchClienti(
            @RequestParam(required = false) Integer fatturatoMin,
            @RequestParam(required = false) Integer fatturatoMax,
            @RequestParam(required = false) String dataInserimento,
            @RequestParam(required = false) String dataUltimoContatto,
            @RequestParam(required = false) String nome,
            Pageable pageable
    ) {
        if (fatturatoMin != null && fatturatoMax != null) {
            return clienteService.filterByFatturato(fatturatoMin, fatturatoMax, pageable);
        }

        if (dataInserimento != null) {
            LocalDate data = LocalDate.parse(dataInserimento);
            return clienteService.filterByDataInserimento(data, pageable);
        }

        if (dataUltimoContatto != null) {
            LocalDate data = LocalDate.parse(dataUltimoContatto);
            return clienteService.filterByDataUltimoContatto(data, pageable);
        }

        if (nome != null) {
            return clienteService.filterByNome(nome, pageable);
        }

        return clienteService.findAll(pageable);
    }
}
