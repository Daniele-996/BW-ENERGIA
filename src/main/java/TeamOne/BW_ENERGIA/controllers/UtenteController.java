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
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Utente> getAll() {
        return utenteService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente getById(@PathVariable Long id) {
        return utenteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
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
        return utenteService.update(utente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente update(@PathVariable Long id, @RequestBody @Valid UtenteDTO dto) {
        Utente existing = utenteService.findById(id);
        existing.setUsername(dto.username());
        existing.setEmail(dto.email());
        existing.setPassword(passwordEncoder.encode(dto.password()));
        existing.setNome(dto.nome());
        existing.setCognome(dto.cognome());
        return utenteService.update(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        utenteService.delete(id);
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadImage(@RequestParam("avatar") MultipartFile file) {
        return this.utenteService.uploadAvatar(file);
    }

    @PatchMapping("/{id}/ruoli")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente updateRuoli(
            @PathVariable Long id,
            @RequestBody List<Long> idRuoli) {
        Utente utente = utenteService.findById(id);
        List<Ruolo> nuoviRuoli = utenteService.getRuoliByIds(idRuoli);
        utente.setRuoli(nuoviRuoli);
        return utenteService.save(utente);
    }
}