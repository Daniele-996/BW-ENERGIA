package TeamOne.BW_ENERGIA.services;

import TeamOne.BW_ENERGIA.entities.Cliente;
import TeamOne.BW_ENERGIA.entities.Indirizzo;
import TeamOne.BW_ENERGIA.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndirizzoService {
    @Autowired
    IndirizzoRepository indirizzoRepository;

    public Page<Indirizzo> findAll(Pageable pageable) {
        return indirizzoRepository.findAll(pageable);
    }

    public Optional<Indirizzo> findById(Long id) {
        return indirizzoRepository.findById(id);
    }

    public Indirizzo save(Indirizzo indirizzo) {
        return indirizzoRepository.save(indirizzo);
    }

    public void deleteById(Long id) {
        indirizzoRepository.deleteById(id);
    }
}
