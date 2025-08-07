package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Ruolo;
import TeamOne.BW_ENERGIA.entities.Utente;
import TeamOne.BW_ENERGIA.payloads.UtenteDTO;
import TeamOne.BW_ENERGIA.services.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //TODO:Renderlo pagebla
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public List<Utente> getAll() {
        return utenteService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public Utente getById(@PathVariable Long id) {
        return utenteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Utente create(@RequestBody @Valid UtenteDTO dto) {
        if (utenteService.existsByEmail(dto.email()) || utenteService.existsByUsername(dto.username())) {
            throw new RuntimeException("Email o username già esistenti");
        }
        Utente utente = new Utente();
        utente.setUsername(dto.username());
        utente.setEmail(dto.email());
        utente.setPassword(passwordEncoder.encode(dto.password()));
        utente.setNome(dto.nome());
        utente.setCognome(dto.cognome());
        return utenteService.save(utente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Utente update(@PathVariable Long id, @RequestBody @Valid UtenteDTO dto) {
        Utente existing = utenteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        existing.setUsername(dto.username());
        existing.setEmail(dto.email());
        existing.setPassword(passwordEncoder.encode(dto.password()));
        existing.setNome(dto.nome());
        existing.setCognome(dto.cognome());
        return utenteService.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        if (!utenteService.findById(id).isPresent()) {
            throw new RuntimeException("Utente non trovato");
        }
        utenteService.delete(id);
    }

    @PatchMapping("/{userId}/avatar")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public String uploadImage(@RequestParam("avatar") MultipartFile file) {
        return this.utenteService.uploadAvatar(file);
    }

    @PatchMapping("/{id}/ruoli")
    @ResponseStatus(HttpStatus.OK)
    public Utente updateRuoli(
            @PathVariable Long id,
            @RequestBody List<Long> idRuoli) {
        return utenteService.findById(id)
                .map(utente -> {
                    List<Ruolo> nuoviRuoli = utenteService.getRuoliByIds(idRuoli);
                    utente.setRuoli(nuoviRuoli);
                    return utenteService.save(utente);
                })
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }
}