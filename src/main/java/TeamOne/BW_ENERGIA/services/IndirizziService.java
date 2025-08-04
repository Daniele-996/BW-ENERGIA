package TeamOne.BW_ENERGIA.services;

import TeamOne.BW_ENERGIA.entities.Indirizzo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndirizziService {
    @Autowired
    IndirizzoRepository indirizzoRepository;

    public Page<Indirizzo> findAll(Pageable pageable) {
        return indirizziRepository.findAll(pageable);
    }

    public Optional<Indirizzo> findById(Long id) {
        return indirizziRepository.findById(id);
    }

    public Indirizzo save(Indirizzo indirizzo) {
        return indirizziRepository.save(indirizzo);
    }

    public void deleteById(Long id) {
        indirizziRepository.deleteById(id);
    }
}
