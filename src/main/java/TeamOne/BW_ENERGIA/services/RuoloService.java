package TeamOne.BW_ENERGIA.services;

import TeamOne.BW_ENERGIA.entities.Ruolo;
import TeamOne.BW_ENERGIA.repositories.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RuoloService {

    @Autowired
    private RuoloRepository ruoloRepository;


    public Optional<Ruolo> findById(Long id) {
        return ruoloRepository.findById(id);
    }

    public Ruolo save(Ruolo ruolo) {
        return ruoloRepository.save(ruolo);
    }
}
