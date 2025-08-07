package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Indirizzo;
import TeamOne.BW_ENERGIA.payloads.IndirizzoDTO;
import TeamOne.BW_ENERGIA.services.IndirizzoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/indirizzi")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Indirizzo> getAll(Pageable pageable) {
        return indirizzoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Indirizzo getById(@PathVariable Long id) {
        return indirizzoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Indirizzo non trovato"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Indirizzo create(@RequestBody @Valid Indirizzo indirizzo) {
        return indirizzoService.save(indirizzo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Indirizzo update(@PathVariable Long id, @RequestBody @Valid IndirizzoDTO dto) {
        Indirizzo existing = indirizzoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Indirizzo non trovato"));
        existing.setVia(dto.via());
        existing.setCivico(dto.civico());
        existing.setLocalita(dto.localita());
        existing.setComune(dto.comune());
        return indirizzoService.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!indirizzoService.findById(id).isPresent()) {
            throw new RuntimeException("Indirizzo non trovato");
        }
        indirizzoService.deleteById(id);
    }

}