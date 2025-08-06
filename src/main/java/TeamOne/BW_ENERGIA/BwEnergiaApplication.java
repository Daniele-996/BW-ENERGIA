package TeamOne.BW_ENERGIA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BwEnergiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BwEnergiaApplication.class, args);
        System.out.println("Applicazione avviata con successo!");

        String URL = "src/main/java/TeamOne/BW_ENERGIA/csv/comuni-italiani.csv";
        importCsvData(URL);
    }

}
