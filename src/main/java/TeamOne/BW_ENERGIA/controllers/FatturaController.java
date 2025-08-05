package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Fattura;
import TeamOne.BW_ENERGIA.payloads.FatturaDTO;
import TeamOne.BW_ENERGIA.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Fattura> getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "15") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return fatturaService.findAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Fattura getById(@PathVariable Long id) {
        return fatturaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura create(@RequestBody @Validated FatturaDTO payload) {
        return fatturaService.creaFattura(payload);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Fattura update(@PathVariable Long id, @RequestBody @Validated FatturaDTO payload) {
        return fatturaService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        fatturaService.findByIdAndDelete(id);
    }
}
