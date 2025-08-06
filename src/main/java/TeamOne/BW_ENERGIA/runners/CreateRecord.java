package TeamOne.BW_ENERGIA.runners;

import TeamOne.BW_ENERGIA.entities.Ruolo;
import TeamOne.BW_ENERGIA.entities.StatoFattura;
import TeamOne.BW_ENERGIA.entities.Utente;
import TeamOne.BW_ENERGIA.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateRecord implements CommandLineRunner {


    @Autowired
    private FatturaService fatturaService;
    @Autowired
    private IndirizzoService indirizzoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private RuoloService ruoloService;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private StatoFatturaService statoFatturaService;


    @Override
    public void run(String... args) throws Exception {
        StatoFattura statoFattura1 = new StatoFattura(0, "Pagata");
        StatoFattura statoFattura2 = new StatoFattura(0, "Non pagata");
        StatoFattura statoFattura3 = new StatoFattura(0, "Da inserire");
        statoFatturaService.save(statoFattura1);
        statoFatturaService.save(statoFattura2);
        statoFatturaService.save(statoFattura3);
        Ruolo ruolo1 = new Ruolo("UTENTE");
        ruoloService.save(ruolo1);
        Ruolo ruolo2 = new Ruolo("ADMIN");
        ruoloService.save(ruolo2);
        Ruolo ruolo3 = new Ruolo("STAGISTA");
        ruoloService.save(ruolo3);
        List<Ruolo> ruoli = List.of(ruolo1, ruolo2, ruolo3);
        Ruolo ruoloDb1 = ruoloService.findById(1L).orElseThrow();
        Ruolo ruoloDb2 = ruoloService.findById(2L).orElseThrow();
        Ruolo ruoloDb3 = ruoloService.findById(3L).orElseThrow();
        Utente utente1 = new Utente(0, "Cicciogamer", "cicciogamer@libero.it", "cicciociccio", "Ciccio", "Gamer", "https://yourdomain.com/images/avatar-placeholder.png", List.of(ruoloDb1));
        Utente utente2 = new Utente(0, "Lellykelly", "lellykelly@libero.it", "lellykelly", "Lelly", "Kelly", "https://yourdomain.com/images/avatar-placeholder.png", List.of(ruoloDb1));
        Utente utente3 = new Utente(0, "JackSparow", "jackjack@libero.it", "jackspaaaar", "Jack", "Sparrow", "https://yourdomain.com/images/avatar-placeholder.png", List.of(ruoloDb3));
        utenteService.save(utente1);
        utenteService.save(utente2);
        utenteService.save(utente3);
    }
}
