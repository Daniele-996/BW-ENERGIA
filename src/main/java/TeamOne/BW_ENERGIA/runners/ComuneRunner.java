package TeamOne.BW_ENERGIA.runners;

import TeamOne.BW_ENERGIA.entities.Comune;
import TeamOne.BW_ENERGIA.entities.Provincia;
import TeamOne.BW_ENERGIA.repositories.ComuneRepository;
import TeamOne.BW_ENERGIA.repositories.ProvinciaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ComuneRunner implements CommandLineRunner {

    private final ComuneRepository comuneRepository;
    private final ProvinciaRepository provinciaRepository;

    public ComuneRunner(ComuneRepository comuneRepository, ProvinciaRepository provinciaRepository) {
        this.comuneRepository = comuneRepository;
        this.provinciaRepository = provinciaRepository;
    }

    @Override
    public void run(String... args) {
        String filePath = new File("src/main/java/TeamOne/BW_ENERGIA/csv/comuni-italiani.csv").getAbsolutePath();
        boolean isFirstLine = true;

        List<Comune> comuniValidi = new ArrayList<>();
        int righeScartate = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (line.trim().isEmpty()) {
                    righeScartate++;
                    continue;
                }

                String[] columns = line.split(";");

                if (columns.length < 4) {
                    righeScartate++;
                    continue;
                }

                String codiceProvinciaStr = columns[0].trim();
                String progressivoStr = columns[1].trim();
                String nomeComune = columns[2].trim();
                String nomeProvincia = columns[3].trim();

                if (nomeComune.isEmpty() || nomeProvincia.isEmpty()) {
                    righeScartate++;
                    continue;
                }

                try {
                    int progressivo = progressivoStr.isEmpty() ? 0 : Integer.parseInt(progressivoStr);

                    Optional<Provincia> provinciaOpt = provinciaRepository.findByNomeIgnoreCase(nomeProvincia);
                    if (provinciaOpt.isEmpty()) {
                        System.out.println("❌ Provincia non trovata per nome: " + nomeProvincia);
                        righeScartate++;
                        continue;
                    }

                    Comune comune = new Comune();
                    comune.setProgressivoComune(progressivo);
                    comune.setNome(nomeComune);
                    comune.setProvincia(provinciaOpt.get());

                    comuniValidi.add(comune);

                } catch (NumberFormatException e) {
                    System.out.println("❌ Progressivo non numerico: " + progressivoStr);
                    righeScartate++;
                } catch (Exception e) {
                    System.out.println("❌ Errore alla riga: " + line);
                    righeScartate++;
                }
            }

            comuneRepository.saveAll(comuniValidi);

            System.out.println("✅ Comuni salvati: " + comuniValidi.size());
            System.out.println("❌ Righe scartate: " + righeScartate);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
