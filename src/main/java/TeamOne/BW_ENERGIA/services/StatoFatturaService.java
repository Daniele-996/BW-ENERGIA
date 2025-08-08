package TeamOne.BW_ENERGIA.services;

import TeamOne.BW_ENERGIA.entities.StatoFattura;
import TeamOne.BW_ENERGIA.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatoFatturaService {
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    public StatoFattura save(StatoFattura statoFattura) {
        return statoFatturaRepository.save(statoFattura);
    }

    public Optional<StatoFattura> findById(Long id) {
        return statoFatturaRepository.findById(id);
    }
}
