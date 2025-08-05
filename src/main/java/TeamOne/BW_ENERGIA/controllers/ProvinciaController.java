package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.payloads.ProvinciaDTO;
import TeamOne.BW_ENERGIA.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/province")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    public List<ProvinciaDTO> getAll() {
        return provinciaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinciaDTO> getById(@PathVariable Long id) {
        return provinciaService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProvinciaDTO> create(@RequestBody ProvinciaDTO dto) {
        ProvinciaDTO created = provinciaService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProvinciaDTO> update(@PathVariable Long id, @RequestBody ProvinciaDTO dto) {
        return provinciaService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (provinciaService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
