package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Comune;
import TeamOne.BW_ENERGIA.payloads.ComuneDTO;
import TeamOne.BW_ENERGIA.services.ComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comuni")
public class ComuneController {
    @Autowired
    private ComuneService comuneService;

    @GetMapping
    public Page<Comune> getAll(Pageable pageable) {
        return comuneService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comune> getById(@PathVariable Long id) {
        return comuneService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Comune create(@RequestBody ComuneDTO dto) {
        return comuneService.save(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comune> update(@PathVariable Long id, @RequestBody ComuneDTO dto) {
        try {
            return ResponseEntity.ok(comuneService.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        comuneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
