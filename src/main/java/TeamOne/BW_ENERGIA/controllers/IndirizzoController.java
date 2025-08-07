package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Comune;
import TeamOne.BW_ENERGIA.entities.Indirizzo;
import TeamOne.BW_ENERGIA.payloads.IndirizzoDTO;
import TeamOne.BW_ENERGIA.repositories.ComuneRepository;
import TeamOne.BW_ENERGIA.services.IndirizzoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/indirizzi")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @Autowired
    private ComuneRepository comuneRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public Page<Indirizzo> getAll(Pageable pageable) {
        return indirizzoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public Indirizzo getById(@PathVariable Long id) {
        return indirizzoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Indirizzo non trovato"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Indirizzo create(@RequestBody @Valid Indirizzo indirizzo) {
        return indirizzoService.save(indirizzo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Indirizzo update(@PathVariable Long id, @RequestBody @Valid IndirizzoDTO dto) {
        Indirizzo existing = indirizzoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Indirizzo non trovato"));
        existing.setVia(dto.via());
        existing.setCivico(dto.civico());
        existing.setLocalita(dto.localita());
        Comune comune = comuneRepository.findById(dto.comuneId())
                .orElseThrow(() -> new RuntimeException("Comune non trovato"));
        existing.setComune(comune);
        return indirizzoService.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        if (!indirizzoService.findById(id).isPresent()) {
            throw new RuntimeException("Indirizzo non trovato");
        }
        indirizzoService.deleteById(id);
    }

}