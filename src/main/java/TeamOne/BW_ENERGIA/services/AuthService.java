package TeamOne.BW_ENERGIA.services;

import TeamOne.BW_ENERGIA.entities.Ruolo;
import TeamOne.BW_ENERGIA.entities.Utente;
import TeamOne.BW_ENERGIA.exceptions.BadRequestException;
import TeamOne.BW_ENERGIA.exceptions.UnauthorizedException;
import TeamOne.BW_ENERGIA.payloads.LoginRequest;
import TeamOne.BW_ENERGIA.payloads.RegisterRequest;
import TeamOne.BW_ENERGIA.repositories.RuoloRepository;
import TeamOne.BW_ENERGIA.repositories.UtenteRepository;
import TeamOne.BW_ENERGIA.tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

@Service
public class AuthService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private RuoloRepository ruoloRepository;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ResponseStatus(HttpStatus.OK)
    public String register(RegisterRequest request) {
        if (utenteRepository.existsByUsername(request.username()))
            throw new BadRequestException("Username già esistente");

        if (utenteRepository.existsByEmail(request.email()))
            throw new BadRequestException("Email già esistente");

        Ruolo ruolo = ruoloRepository.findByRuolo("UTENTE");
        if (ruolo == null)
            throw new BadRequestException("Ruolo UTENTE non trovato");

        Utente utente = new Utente();
        utente.setUsername(request.username());
        utente.setEmail(request.email());
        utente.setPassword(passwordEncoder.encode(request.password()));
        utente.setNome(request.nome());
        utente.setCognome(request.cognome());
        utente.setRuoli(Collections.singletonList(ruolo));

        utenteRepository.save(utente);
        return "Registrazione avvenuta con successo";
    }

    @ResponseStatus(HttpStatus.OK)
    public String login(LoginRequest request) {
        Utente utente = utenteRepository.findByUsername(request.username())
                .orElseThrow(() -> new UnauthorizedException("Username o password non validi"));

        if (!passwordEncoder.matches(request.password(), utente.getPassword()))
            throw new UnauthorizedException("Username o password non validi");

        return jwtTools.createToken(utente);
    }

    @Bean
    public PasswordEncoder getBcrypt() {
        return new BCryptPasswordEncoder(12);
    }
}