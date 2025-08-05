package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.services.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/csv")
public class CsvImportController {

    @Autowired
    private CsvImportService csvImportService;

    @PostMapping("/import")
    public ResponseEntity<String> importCsv(@RequestParam String filePath) {
        try {
            csvImportService.importCsvData(filePath);
            return ResponseEntity.ok("CSV data imported successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error importing CSV data: " + e.getMessage());
        }
    }
}
