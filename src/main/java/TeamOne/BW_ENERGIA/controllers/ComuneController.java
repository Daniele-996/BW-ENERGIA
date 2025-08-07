package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Comune;
import TeamOne.BW_ENERGIA.payloads.ComuneDTO;
import TeamOne.BW_ENERGIA.services.ComuneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comuni")
public class ComuneController {

    @Autowired
    private ComuneService comuneService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Comune> getAll(Pageable pageable) {
        return comuneService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comune getById(@PathVariable Long id) {
        return comuneService.findById(id)
                .orElseThrow(() -> new RuntimeException("Comune non trovato"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comune create(@RequestBody @Valid ComuneDTO dto) {
        return comuneService.save(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!comuneService.findById(id).isPresent()) {
            throw new RuntimeException("Comune non trovato");
        }
        comuneService.deleteById(id);
    }
}