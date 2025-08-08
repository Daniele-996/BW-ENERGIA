package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.payloads.ProvinciaDTO;
import TeamOne.BW_ENERGIA.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/province")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProvinciaDTO> getAll() {
        return provinciaService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProvinciaDTO getById(@PathVariable Long id) {
        return provinciaService.getById(id)
                .orElseThrow(() -> new RuntimeException("Provincia non trovata"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProvinciaDTO create(@RequestBody ProvinciaDTO dto) {
        return provinciaService.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProvinciaDTO update(@PathVariable Long id, @RequestBody ProvinciaDTO dto) {
        return provinciaService.update(id, dto)
                .orElseThrow(() -> new RuntimeException("Provincia non trovata"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!provinciaService.delete(id)) {
            throw new RuntimeException("Provincia non trovata");
        }
    }
}