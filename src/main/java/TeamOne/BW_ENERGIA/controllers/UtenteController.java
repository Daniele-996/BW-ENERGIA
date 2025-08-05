package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.entities.Utente;
import TeamOne.BW_ENERGIA.payloads.UtenteDTO;
import TeamOne.BW_ENERGIA.services.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<Utente> getAll() {
        return utenteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getById(@PathVariable Long id) {
        return utenteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Utente> create(@RequestBody @Valid UtenteDTO dto) {
        if (utenteService.existsByEmail(dto.email()) || utenteService.existsByUsername(dto.username())) {
            return ResponseEntity.badRequest().build();
        }

        Utente utente = new Utente();
        utente.setUsername(dto.username());
        utente.setEmail(dto.email());
        utente.setPassword(passwordEncoder.encode(dto.password()));
        utente.setNome(dto.nome());
        utente.setCognome(dto.cognome());
        // utente.setAvatar(dto.avatar());
        // utente.setRuoli(dto.idRuoli());

        return ResponseEntity.ok(utenteService.save(utente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utente> update(@PathVariable Long id, @RequestBody @Valid UtenteDTO dto) {
        return utenteService.findById(id)
                .map(existing -> {
                    existing.setUsername(dto.username());
                    existing.setEmail(dto.email());
                    existing.setPassword(passwordEncoder.encode(dto.password()));
                    existing.setNome(dto.nome());
                    existing.setCognome(dto.cognome());
                    // existing.setAvatar(dto.avatar());
                    // existing.setRuoli(dto.idRuoli());
                    return ResponseEntity.ok(utenteService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!utenteService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        utenteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/avatar")
    public String uploadImage(@RequestParam("avatar") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        return this.utenteService.uploadAvatar(file);

    }
}