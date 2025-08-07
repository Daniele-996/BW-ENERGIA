package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Indirizzo;
import TeamOne.BW_ENERGIA.payloads.IndirizzoDTO;
import TeamOne.BW_ENERGIA.services.IndirizzoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/indirizzi")
public class IndirizzoController {
    @Autowired
    IndirizzoService indirizzoService;


    @GetMapping
    public Page<Indirizzo> getAll(Pageable pageable) {
        return indirizzoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Indirizzo> getById(@PathVariable Long id) {
        return indirizzoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Indirizzo create(@RequestBody Indirizzo indirizzo) {
        return indirizzoService.save(indirizzo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Indirizzo> update(@PathVariable Long id, @RequestBody @Valid IndirizzoDTO dto) {
        return indirizzoService.findById(id)
                .map(existing -> {
                    existing.setVia(dto.via());
                    existing.setCivico(dto.civico());
                    existing.setLocalita(dto.localita());
                    existing.setComune(dto.comune());
                    return ResponseEntity.ok(indirizzoService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (indirizzoService.findById(id).isPresent()) {
            indirizzoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
