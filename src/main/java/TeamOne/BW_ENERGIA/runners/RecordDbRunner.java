package TeamOne.BW_ENERGIA.runners;

import TeamOne.BW_ENERGIA.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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


    @Override
    public void run(String... args) throws Exception {

        if (fatturaService.findAll().isEmpty()){
            System.out.print("Non ci sono fatture");
        } else {
            System.out.print("Sono presenti " + fatturaService.findAll().size() + "fatture nel database");
        }
        if (indirizzoService.findAll().isEmpty()){
            System.out.print("Non ci sono indirizzi");
        } else {
            System.out.print("Sono presenti " + indirizzoService.findAll().size() + "indirizzi nel database");
        }
        if (clienteService.findAll().isEmpty()){
            System.out.print("Non ci sono fatture");
        } else {
            System.out.print("Sono presenti " + clienteService.findAll().size() + "clienti nel database");
        }
        if (ruoloService.findAll().isEmpty()){
            System.out.print("Non ci sono fatture");
        } else {
            System.out.print("Sono presenti " + ruoloService.findAll().size() + "ruoli nel database");
        }
        if (UtenteService.findAll().isEmpty()){
            System.out.print("Non ci sono fatture");
        } else {
            System.out.print("Sono presenti " + utenteService.findAll().size() + "utenti nel database");
        }

    }
}
