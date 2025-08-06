package TeamOne.BW_ENERGIA.services;

import TeamOne.BW_ENERGIA.entities.Comune;
import TeamOne.BW_ENERGIA.repositories.ComuneRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.List;

@Service
public class CsvImportService {

    @Autowired
    private ComuneRepository comuneRepository;

    public void importCsvData(String filePath) throws Exception {
        List<Comune> comuni = new CsvToBeanBuilder<Comune>(new FileReader(filePath))
                .withType(Comune.class)
                .build()
                .parse();
        System.out.println(comuni);
    }
}
