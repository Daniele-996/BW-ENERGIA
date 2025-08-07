package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Fattura;
import TeamOne.BW_ENERGIA.payloads.FatturaDTO;
import TeamOne.BW_ENERGIA.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public Page<Fattura> getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "15") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return fatturaService.findAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public Fattura getById(@PathVariable Long id) {
        return fatturaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Fattura create(@RequestBody @Validated FatturaDTO payload) {
        return fatturaService.creaFattura(payload);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Fattura update(@PathVariable Long id, @RequestBody @Validated FatturaDTO payload) {
        return fatturaService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        fatturaService.findByIdAndDelete(id);
    }


    // Ricerca e filtro
//   GET /api/fatture/search?clienteId=5
//   GET /api/fatture/search?statoFattura=Pagata
//   GET /api/fatture/search?data=2025-08-01
//   GET /api/fatture/search?anno=2025
//   GET /api/fatture/search?importoMin=100&importoMax=500
    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public Page<Fattura> searchFatture(
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) String statoFattura,
            @RequestParam(required = false) String data, // formato: yyyy-MM-dd
            @RequestParam(required = false) Integer anno,
            @RequestParam(required = false) Integer importoMin,
            @RequestParam(required = false) Integer importoMax,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

        if (clienteId != null) {
            return fatturaService.filterByClienteId(clienteId, pageable);
        }

        if (statoFattura != null) {
            return fatturaService.filterByStato(statoFattura, pageable);
        }

        if (data != null) {
            LocalDate parsedDate = LocalDate.parse(data);
            return fatturaService.filterByData(parsedDate, pageable);
        }

        if (anno != null) {
            return fatturaService.filterByAnno(anno, pageable);
        }

        if (importoMin != null && importoMax != null) {
            return fatturaService.filterByImportoRange(importoMin, importoMax, pageable);
        }

        return fatturaService.findAll(page, size, sortBy);
    }
}
