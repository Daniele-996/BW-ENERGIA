package TeamOne.BW_ENERGIA.services;

import TeamOne.BW_ENERGIA.entities.Ruolo;
import TeamOne.BW_ENERGIA.repositories.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class RuoloService {

    @Autowired
    private RuoloRepository ruoloRepository;

    public Optional<Ruolo> findById(Long id) {
        return ruoloRepository.findById(id);
    }

    public Map<Object, Object> findAll() {
        return ruoloRepository.findAll().stream()
                .collect(java.util.stream.Collectors.toMap(
                        Ruolo::getId,
                        ruolo -> {
                            Map<String, Object> mappa = new java.util.HashMap<>();
                            mappa.put("nome", ruolo.getId());
                            mappa.put("descrizione", ruolo.getRuolo());
                            return mappa;
                        }
                ));
    }
}
