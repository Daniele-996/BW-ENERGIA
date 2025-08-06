package TeamOne.BW_ENERGIA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class BwEnergiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BwEnergiaApplication.class, args);
        System.out.println("Applicazione avviata con successo!");



       /* Utente utente = new Utente();
        utente.setUsername();
        utente.setEmail();
        utente.setPassword();
        utente.setNome();
        utente.setCognome();
        utente.setAvatar();
        utente.setRuolo(Ruolo.ADMIN);
        */
    }

}
