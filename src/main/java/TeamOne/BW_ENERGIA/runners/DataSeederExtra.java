package TeamOne.BW_ENERGIA.runners;

import TeamOne.BW_ENERGIA.entities.*;
import TeamOne.BW_ENERGIA.enums.Tipo;
import TeamOne.BW_ENERGIA.repositories.ComuneRepository;
import TeamOne.BW_ENERGIA.repositories.ProvinciaRepository;
import TeamOne.BW_ENERGIA.repositories.StatoFatturaRepository;
import TeamOne.BW_ENERGIA.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataSeederExtra implements CommandLineRunner {

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
    private ComuneRepository comuneRepository;
    @Autowired
    private ProvinciaRepository provinciaRepository;
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;
    @Autowired
    private StatoFatturaService statoFatturaService;

    @Override
    public void run(String... args) throws Exception {
        boolean dbPopolato = !utenteService.findAll().isEmpty()
                || !fatturaService.findAll(0, 15, "id").isEmpty()
                || !clienteService.findAll(Pageable.unpaged()).getContent().isEmpty();

        if (dbPopolato) {
            System.out.println("Database già popolato, seeder non eseguito.");
            return;
        }

        // Seeder StatoFattura
        StatoFattura statoFattura1 = new StatoFattura(0, "Pagata");
        StatoFattura statoFattura2 = new StatoFattura(0, "Non pagata");
        StatoFattura statoFattura3 = new StatoFattura(0, "Da inserire");
        StatoFattura statoFattura4 = new StatoFattura(0, "In attesa");
        StatoFattura statoFattura5 = new StatoFattura(0, "Annullata");
        statoFatturaService.save(statoFattura1);
        statoFatturaService.save(statoFattura2);
        statoFatturaService.save(statoFattura3);
        statoFatturaService.save(statoFattura4);
        statoFatturaService.save(statoFattura5);

        // Seeder Ruoli
        Ruolo ruolo1 = new Ruolo("UTENTE");
        Ruolo ruolo2 = new Ruolo("ADMIN");
        Ruolo ruolo3 = new Ruolo("STAGISTA");
        Ruolo ruolo4 = new Ruolo("SUPERVISORE");
        ruoloService.save(ruolo1);
        ruoloService.save(ruolo2);
        ruoloService.save(ruolo3);
        ruoloService.save(ruolo4);

        // Seeder Utenti
        Ruolo ruoloDb1 = ruoloService.findById(1L).orElseThrow();
        Ruolo ruoloDb2 = ruoloService.findById(2L).orElseThrow();
        Ruolo ruoloDb3 = ruoloService.findById(3L).orElseThrow();
        Ruolo ruoloDb4 = ruoloService.findById(4L).orElseThrow();
        Utente utente1 = new Utente(0, "Cicciogamer", "cicciogamer@libero.it", passwordEncoder.encode("cicciociccio"), "Ciccio", "Gamer", "https://yourdomain.com/images/avatar-placeholder.png", List.of(ruoloDb1));
        Utente utente2 = new Utente(0, "Lellykelly", "lellykelly@libero.it", passwordEncoder.encode("lellykelly"), "Lelly", "Kelly", "https://yourdomain.com/images/avatar-placeholder.png", List.of(ruoloDb1));
        Utente utente3 = new Utente(0, "JackSparow", "jackjack@libero.it", passwordEncoder.encode("jackspaaaar"), "Jack", "Sparrow", "https://yourdomain.com/images/avatar-placeholder.png", List.of(ruoloDb3));
        Utente utente4 = new Utente(0, "AdminUser", "admin@domain.com", passwordEncoder.encode("adminpass"), "Mario", "Rossi", "https://yourdomain.com/images/avatar-placeholder.png", List.of(ruoloDb2));
        Utente utente5 = new Utente(0, "Supervisor", "supervisor@domain.com", passwordEncoder.encode("supervisorpass"), "Anna", "Verdi", "https://yourdomain.com/images/avatar-placeholder.png", List.of(ruoloDb4));
        utenteService.save(utente1);
        utenteService.save(utente2);
        utenteService.save(utente3);
        utenteService.save(utente4);
        utenteService.save(utente5);

        // Seeder Province
        Provincia provincia1 = new Provincia();
        provincia1.setNome("ProvinciaTest");
        provincia1.setSigla("PT");
        provincia1.setRegione("RegioneTest");
        provincia1 = provinciaRepository.save(provincia1);

        Provincia provincia2 = new Provincia();
        provincia2.setNome("Milano");
        provincia2.setSigla("MI");
        provincia2.setRegione("Lombardia");
        provincia2 = provinciaRepository.save(provincia2);

        // Seeder Comuni
        Comune comune1 = new Comune();
        comune1.setNome("ComuneTest");
        comune1.setProvincia(provincia1);
        comune1 = comuneRepository.save(comune1);

        Comune comune2 = new Comune();
        comune2.setNome("Milano");
        comune2.setProvincia(provincia2);
        comune2 = comuneRepository.save(comune2);

        Comune comune3 = new Comune();
        comune3.setNome("Sesto San Giovanni");
        comune3.setProvincia(provincia2);
        comune3 = comuneRepository.save(comune3);

        // Seeder Indirizzi
        Indirizzo indirizzo1 = new Indirizzo();
        indirizzo1.setVia("Via Roma");
        indirizzo1.setCivico(1);
        indirizzo1.setLocalita("Centro");
        indirizzo1.setComune(comune1);
        indirizzoService.save(indirizzo1);

        Indirizzo indirizzo2 = new Indirizzo();
        indirizzo2.setVia("Via Milano");
        indirizzo2.setCivico(10);
        indirizzo2.setLocalita("Nord");
        indirizzo2.setComune(comune2);
        indirizzoService.save(indirizzo2);

        Indirizzo indirizzo3 = new Indirizzo();
        indirizzo3.setVia("Via Dante");
        indirizzo3.setCivico(5);
        indirizzo3.setLocalita("Sud");
        indirizzo3.setComune(comune3);
        indirizzoService.save(indirizzo3);

        // Seeder Clienti
        Cliente cliente1 = new Cliente();
        cliente1.setRagioneSociale("Azienda Srl");
        cliente1.setPartitaIva(123456789);
        cliente1.setEmail("azienda@email.com");
        cliente1.setDataInserimento(LocalDate.now());
        cliente1.setDataUltimoContatto(LocalDate.now());
        cliente1.setFatturatoAnnuale(100000);
        cliente1.setPec("pec@azienda.com");
        cliente1.setTelefono(123456789);
        cliente1.setEmailContatto("contatto@azienda.com");
        cliente1.setNomeContatto("Luca");
        cliente1.setCognomeContatto("Bianchi");
        cliente1.setTelefonoContatto(987654321);
        cliente1.setLogoAziendale("logo.png");
        cliente1.setTipo(Tipo.SRL);
        cliente1.setIndirizzoSedeOp(indirizzo1);
        cliente1.setIndirizzoLegale(indirizzo1);
        clienteService.save(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setRagioneSociale("Beta Spa");
        cliente2.setPartitaIva(987654321);
        cliente2.setEmail("beta@azienda.com");
        cliente2.setDataInserimento(LocalDate.now().minusDays(10));
        cliente2.setDataUltimoContatto(LocalDate.now().minusDays(2));
        cliente2.setFatturatoAnnuale(250000);
        cliente2.setPec("pec@beta.com");
        cliente2.setTelefono(222333444);
        cliente2.setEmailContatto("contatto@beta.com");
        cliente2.setNomeContatto("Marco");
        cliente2.setCognomeContatto("Verdi");
        cliente2.setTelefonoContatto(123123123);
        cliente2.setLogoAziendale("beta_logo.png");
        cliente2.setTipo(Tipo.SPA);
        cliente2.setIndirizzoSedeOp(indirizzo2);
        cliente2.setIndirizzoLegale(indirizzo2);
        clienteService.save(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.setRagioneSociale("Gamma SNC");
        cliente3.setPartitaIva(555666777);
        cliente3.setEmail("gamma@azienda.com");
        cliente3.setDataInserimento(LocalDate.now().minusMonths(1));
        cliente3.setDataUltimoContatto(LocalDate.now().minusDays(5));
        cliente3.setFatturatoAnnuale(50000);
        cliente3.setPec("pec@gamma.com");
        cliente3.setTelefono(444555666);
        cliente3.setEmailContatto("contatto@gamma.com");
        cliente3.setNomeContatto("Giulia");
        cliente3.setCognomeContatto("Neri");
        cliente3.setTelefonoContatto(321321321);
        cliente3.setLogoAziendale("gamma_logo.png");
        cliente3.setTipo(Tipo.SAS);
        cliente3.setIndirizzoSedeOp(indirizzo3);
        cliente3.setIndirizzoLegale(indirizzo3);
        clienteService.save(cliente3);

        // Seeder Fatture
        StatoFattura statoFatturaDb1 = statoFatturaRepository.findAll().get(0);
        StatoFattura statoFatturaDb2 = statoFatturaRepository.findAll().get(1);
        StatoFattura statoFatturaDb3 = statoFatturaRepository.findAll().get(2);

        Fattura fattura1 = new Fattura();
        fattura1.setData(LocalDate.now());
        fattura1.setImporto(500);
        fattura1.setNumero(1);
        fattura1.setCliente(cliente1);
        fattura1.setStatoFattura(statoFatturaDb1);
        fatturaService.fatturaRepository.save(fattura1);

        Fattura fattura2 = new Fattura();
        fattura2.setData(LocalDate.now().minusDays(3));
        fattura2.setImporto(1200);
        fattura2.setNumero(2);
        fattura2.setCliente(cliente2);
        fattura2.setStatoFattura(statoFatturaDb2);
        fatturaService.fatturaRepository.save(fattura2);

        Fattura fattura3 = new Fattura();
        fattura3.setData(LocalDate.now().minusMonths(1));
        fattura3.setImporto(300);
        fattura3.setNumero(3);
        fattura3.setCliente(cliente3);
        fattura3.setStatoFattura(statoFatturaDb3);
        fatturaService.fatturaRepository.save(fattura3);

        System.out.println("Database popolato con dati di test extra.");
    }
}