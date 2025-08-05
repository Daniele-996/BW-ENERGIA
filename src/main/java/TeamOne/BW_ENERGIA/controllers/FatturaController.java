package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Fattura;
import TeamOne.BW_ENERGIA.payloads.FatturaDTO;
import TeamOne.BW_ENERGIA.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    public List<Fattura> getAllFatture() {
        return fatturaService.findAll();
    }

    @GetMapping("/{id}")
    public Fattura getById(@PathVariable Long id) {
        return fatturaService.findById(id);
    }

    @PostMapping
    public Fattura create(@RequestBody @Validated FatturaDTO payload) {
        return fatturaService.creaFattura(payload);
    }

    @PutMapping("/{id}")
    public Fattura update(@PathVariable Long id, @RequestBody @Validated FatturaDTO payload) {
        return fatturaService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fatturaService.findByIdAndDelete(id);
    }
}
