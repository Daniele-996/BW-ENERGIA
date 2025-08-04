package TeamOne.BW_ENERGIA.services;

import TeamOne.BW_ENERGIA.entities.Comune;
import TeamOne.BW_ENERGIA.entities.Provincia;
import TeamOne.BW_ENERGIA.payloads.ComuneDTO;
import TeamOne.BW_ENERGIA.repositories.ComuneRepository;
import TeamOne.BW_ENERGIA.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComuneService {
    @Autowired
    private ComuneRepository comuneRepository;
    @Autowired
    private ProvinciaRepository provinciaRepository;

    public Page<Comune> findAll(Pageable pageable) {
        return comuneRepository.findAll(pageable);
    }

    public Optional<Comune> findById(Long id) {
        return comuneRepository.findById(id);
    }

    public Comune save(ComuneDTO dto) {
        Provincia provincia = provinciaRepository.findById(dto.provinciaId())
                .orElseThrow(() -> new RuntimeException("Provincia non trovata"));
        Comune comune = new Comune();
        comune.setNome(dto.nome());
        comune.setProvincia(provincia);
        return comuneRepository.save(comune);
    }

    public Comune update(Long id, ComuneDTO dto) {
        Comune comune = comuneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comune non trovato"));
        comune.setNome(dto.nome());
        Provincia provincia = provinciaRepository.findById(dto.provinciaId())
                .orElseThrow(() -> new RuntimeException("Provincia non trovata"));
        comune.setProvincia(provincia);
        return comuneRepository.save(comune);
    }

    public void deleteById(Long id) {
        comuneRepository.deleteById(id);
    }
}
