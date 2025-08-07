package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Cliente;
import TeamOne.BW_ENERGIA.services.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Cliente> getAll(Pageable pageable) {
        return clienteService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente getById(@PathVariable Long id) {
        return clienteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente non trovato"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente existing = clienteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente non trovato"));
        BeanUtils.copyProperties(cliente, existing, "id", "fatture");
        return clienteService.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!clienteService.findById(id).isPresent()) {
            throw new RuntimeException("Cliente non trovato");
        }
        clienteService.deleteById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
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