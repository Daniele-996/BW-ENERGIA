package TeamOne.BW_ENERGIA.services;

import TeamOne.BW_ENERGIA.entities.Provincia;
import TeamOne.BW_ENERGIA.payloads.ProvinciaDTO;
import TeamOne.BW_ENERGIA.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public List<ProvinciaDTO> getAll() {
        return provinciaRepository.findAll().stream().map(this::toDTO).toList();
    }

    public Optional<ProvinciaDTO> getById(Long id) {
        return provinciaRepository.findById(id).map(this::toDTO);
    }

    public ProvinciaDTO create(ProvinciaDTO dto) {
        Provincia provincia = new Provincia();
        provincia.setSigla(dto.sigla());
        provincia.setNome(dto.nome());
        provincia.setRegione(dto.regione());
        Provincia saved = provinciaRepository.save(provincia);
        return toDTO(saved);
    }

    public Optional<ProvinciaDTO> update(Long id, ProvinciaDTO dto) {
        Optional<Provincia> optionalProvincia = provinciaRepository.findById(id);
        if (optionalProvincia.isPresent()) {
            Provincia provincia = optionalProvincia.get();
            provincia.setSigla(dto.sigla());
            provincia.setNome(dto.nome());
            provincia.setRegione(dto.regione());
            Provincia updated = provinciaRepository.save(provincia);
            return Optional.of(toDTO(updated));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (provinciaRepository.existsById(id)) {
            provinciaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProvinciaDTO toDTO(Provincia provincia) {
        return new ProvinciaDTO(
                provincia.getId(),
                provincia.getSigla(),
                provincia.getNome(),
                provincia.getRegione()
        );
    }
}