 package TeamOne.BW_ENERGIA.runners;

import TeamOne.BW_ENERGIA.entities.*;
import TeamOne.BW_ENERGIA.enums.Tipo;
import TeamOne.BW_ENERGIA.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
public class RecordDbRunner implements CommandLineRunner {

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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TeamOne.BW_ENERGIA.repositories.ComuneRepository comuneRepository;
    @Autowired
    private TeamOne.BW_ENERGIA.repositories.StatoFatturaRepository statoFatturaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Popola Ruoli
        if (ruoloService.findAll().isEmpty()) {
            Ruolo ruolo = new Ruolo();
            ruolo.setRuolo("UTENTE");
            ruoloService.ruoloRepository.save(ruolo);
            System.out.println("Ruolo UTENTE creato");
        }

        // Popola Utenti
        if (utenteService.findAll().isEmpty()) {
            Ruolo ruolo = ruoloService.ruoloRepository.findByRuolo("UTENTE");
            Utente utente = new Utente();
            utente.setUsername("testuser");
            utente.setEmail("test@example.com");
            utente.setPassword(passwordEncoder.encode("testpassword"));
            utente.setNome("Mario");
            utente.setCognome("Rossi");
            utente.setRuoli(Collections.singletonList(ruolo));
            utenteService.save(utente);
            System.out.println("Utente di test creato");
        }

        // Popola Indirizzi
        if (indirizzoService.findAll(Pageable.unpaged()).isEmpty()) {
            Comune comune = comuneRepository.findAll().stream().findFirst().orElse(null);
            if (comune == null) {
                comune = new Comune();
                comune.setNome("ComuneTest");
                // Imposta anche la provincia se necessario
            }
            Indirizzo indirizzo = new Indirizzo();
            indirizzo.setVia("Via Roma");
            indirizzo.setCivico(1);
            indirizzo.setLocalita("Centro");
            indirizzo.setComune(comune);
            indirizzoService.save(indirizzo);
            System.out.println("Indirizzo di test creato");
        }

        // Popola Clienti
        if (clienteService.findAll(Pageable.unpaged()).isEmpty()) {
            List<Indirizzo> indirizzi = indirizzoService.findAll(Pageable.unpaged()).getContent();
            Indirizzo indirizzo = indirizzi.get(0);
            Cliente cliente = new Cliente();
            cliente.setRagioneSociale("Azienda Srl");
            cliente.setPartitaIva(123456789);
            cliente.setEmail("azienda@email.com");
            cliente.setDataInserimento(LocalDate.now());
            cliente.setDataUltimoContatto(LocalDate.now());
            cliente.setFatturatoAnnuale(100000);
            cliente.setPec("pec@azienda.com");
            cliente.setTelefono(123456789);
            cliente.setEmailContatto("contatto@azienda.com");
            cliente.setNomeContatto("Luca");
            cliente.setCognomeContatto("Bianchi");
            cliente.setTelefonoContatto(987654321);
            cliente.setLogoAziendale("logo.png");
            cliente.setTipo(Tipo.SRL);
            cliente.setIndirizzoSedeOp(indirizzo);
            cliente.setIndirizzoLegale(indirizzo);
            clienteService.save(cliente);
            System.out.println("Cliente di test creato");
        }

        // Popola StatoFattura se necessario
        StatoFattura statoFattura = statoFatturaRepository.findAll().stream().findFirst().orElse(null);
        if (statoFattura == null) {
            statoFattura = new StatoFattura();
            statoFattura.setStato("PAGATA");
            statoFattura = statoFatturaRepository.save(statoFattura);
        }

        // Popola Fatture
        if (fatturaService.findAll().isEmpty()) {
            Cliente cliente = clienteService.findAll(Pageable.unpaged()).getContent().get(0);
            Fattura fattura = new Fattura();
            fattura.setData(LocalDate.now());
            fattura.setImporto(500);
            fattura.setNumero(1);
            fattura.setCliente(cliente);
            fattura.setStatoFattura(statoFattura);
            fatturaService.fatturaRepository.save(fattura);
            System.out.println("Fattura di test creata");
        }
    }
}