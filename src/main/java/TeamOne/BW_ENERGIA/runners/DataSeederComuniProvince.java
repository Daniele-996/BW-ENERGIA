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
import java.util.Map;
import java.util.Optional;

//@Component
public class DataSeederComuniProvince implements CommandLineRunner {

    private final ProvinciaRepository provinciaRepository;
    private final ComuneRepository comuneRepository;

    public DataSeederComuniProvince(ComuneRepository comuneRepository, ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
        this.comuneRepository = comuneRepository;
    }

    @Override
    public void run(String... args) {
        if (provinciaRepository.count() > 0 || comuneRepository.count() > 0) {
            System.out.println("Seeder non eseguito: province o comuni già presenti nel database.");
            return;
        }

        popolaProvince();
        popolaComuni();
    }

    private void popolaProvince() {
        String filePath = new File("src/main/java/TeamOne/BW_ENERGIA/csv/province-italiane.csv").getAbsolutePath();
        boolean isFirstLine = true;

        int salvate = 0;
        int scartate = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
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

    private void popolaComuni() {
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
                        System.out.println("Progressivo non valido (" + progressivoStr + "), assegnato fittizio: " + progressivo);
                    }

                    String nomeProvinciaNormalizzato = normalizzaNomeProvincia(nomeProvinciaRaw);
                    Optional<Provincia> provinciaOpt = provinciaRepository.findByNomeIgnoreCase(nomeProvinciaNormalizzato);

                    if (provinciaOpt.isEmpty()) {
                        System.out.println("Provincia non trovata per nome: " + nomeProvinciaRaw + " ➜ cercato come: " + nomeProvinciaNormalizzato);
                        righeScartate++;
                        continue;
                    }

                    Comune comune = new Comune();
                    comune.setProgressivoComune(progressivo);
                    comune.setNome(nomeComune);
                    comune.setProvincia(provinciaOpt.get());

                    comuniValidi.add(comune);

                } catch (Exception e) {
                    System.out.println("Errore imprevisto alla riga: " + line);
                    righeScartate++;
                }
            }

            comuneRepository.saveAll(comuniValidi);
            System.out.println("Comuni salvati: " + comuniValidi.size());
            System.out.println("Righe scartate: " + righeScartate);

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
