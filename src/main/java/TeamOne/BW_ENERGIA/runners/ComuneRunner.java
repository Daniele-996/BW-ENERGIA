package TeamOne.BW_ENERGIA.runners;

import TeamOne.BW_ENERGIA.entities.Comune;
import TeamOne.BW_ENERGIA.entities.Provincia;
import TeamOne.BW_ENERGIA.repositories.ComuneRepository;
import TeamOne.BW_ENERGIA.repositories.ProvinciaRepository;
import org.springframework.boot.CommandLineRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Component
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
        int progressivoFittizio = 000;

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

                try {
                    String progressivoStr = columns[1].trim();
                    String nomeComune = columns[2].trim();
                    String nomeProvinciaRaw = columns[3].trim();

                    if (nomeComune.isEmpty() || nomeProvinciaRaw.isEmpty()) {
                        righeScartate++;
                        continue;
                    }

                    int progressivo;
                    try {
                        progressivo = Integer.parseInt(progressivoStr);
                    } catch (NumberFormatException e) {
                        progressivo = progressivoFittizio++;
                        System.out.println("⚠️ Progressivo non valido (" + progressivoStr + "), assegnato fittizio: " + progressivo);
                    }

                    String nomeProvinciaNormalizzato = normalizzaNomeProvincia(nomeProvinciaRaw);
                    Optional<Provincia> provinciaOpt = provinciaRepository.findByNomeIgnoreCase(nomeProvinciaNormalizzato);

                    if (provinciaOpt.isEmpty()) {
                        System.out.println("❌ Provincia non trovata per nome: " + nomeProvinciaRaw + " ➜ cercato come: " + nomeProvinciaNormalizzato);
                        righeScartate++;
                        continue;
                    }

                    Comune comune = new Comune();
                    comune.setProgressivoComune(progressivo);
                    comune.setNome(nomeComune);
                    comune.setProvincia(provinciaOpt.get());

                    comuniValidi.add(comune);

                } catch (Exception e) {
                    System.out.println("❌ Errore imprevisto alla riga: " + line);
                    righeScartate++;
                }
            }

            comuneRepository.saveAll(comuniValidi);
            System.out.println("✅ Comuni salvati: " + comuniValidi.size());
            System.out.println("⚠️  Righe scartate: " + righeScartate);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String normalizzaNomeProvincia(String nomeProvinciaRaw) {
        String nome = nomeProvinciaRaw.trim();

        Map<String, String> correzioniManuali = Map.ofEntries(
                Map.entry("Bolzano/Bozen", "Bolzano"),
                Map.entry("Valle d'Aosta/Vallée d'Aoste", "Aosta"),
                Map.entry("Monza e della Brianza", "Monza-Brianza"),
                Map.entry("Reggio nell'Emilia", "Reggio-Emilia"),
                Map.entry("Forlì-Cesena", "Forli-Cesena"),
                Map.entry("Pesaro e Urbino", "Pesaro-Urbino"),
                Map.entry("La Spezia", "La-Spezia"),
                Map.entry("Reggio Calabria", "Reggio-Calabria"),
                Map.entry("Vibo Valentia", "Vibo-Valentia"),
                Map.entry("Verbano-Cusio-Ossola", "Verbania"),
                Map.entry("Ascoli Piceno", "Ascoli-Piceno"),
                Map.entry("Sud Sardegna", "Carbonia Iglesias"),
                Map.entry("Olbia-Tempio", "Olbia Tempio"),
                Map.entry("Medio Campidano", "Medio Campidano")
        );

        if (correzioniManuali.containsKey(nome)) {
            return correzioniManuali.get(nome);
        }

        if (nome.contains("/")) {
            nome = nome.split("/")[0].trim();
        }

        nome = nome.replaceAll("\\s+", "-");
        return nome;
    }
}
