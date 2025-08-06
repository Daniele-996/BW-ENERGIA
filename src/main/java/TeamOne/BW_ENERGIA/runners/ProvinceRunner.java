package TeamOne.BW_ENERGIA.runners;

import TeamOne.BW_ENERGIA.entities.Provincia;
import TeamOne.BW_ENERGIA.repositories.ProvinciaRepository;
import org.springframework.boot.CommandLineRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

//@Component
public class ProvinceRunner implements CommandLineRunner {

    private final ProvinciaRepository provinciaRepository;

    public ProvinceRunner(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    @Override
    public void run(String... args) {
        String filePath = new File("src/main/java/TeamOne/BW_ENERGIA/csv/province-italiane.csv").getAbsolutePath();
        boolean isFirstLine = true;

        int salvate = 0;
        int scartate = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // salta intestazione
                }

                if (line.trim().isEmpty()) {
                    scartate++;
                    continue;
                }

                String[] columns = line.split(";");

                if (columns.length < 3) {
                    scartate++;
                    continue;
                }

                String sigla = columns[0].trim();
                String nome = columns[1].trim();
                String regione = columns[2].trim();

                if (sigla.isEmpty() || nome.isEmpty() || regione.isEmpty()) {
                    scartate++;
                    continue;
                }

                Optional<Provincia> existing = provinciaRepository.findBySigla(sigla);
                if (existing.isPresent()) {
                    continue;
                }

                Provincia provincia = new Provincia();
                provincia.setSigla(sigla);
                provincia.setNome(nome);
                provincia.setRegione(regione);

                provinciaRepository.save(provincia);
                salvate++;
            }

            System.out.println("Province salvate: " + salvate);
            System.out.println("Province scartate: " + scartate);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
