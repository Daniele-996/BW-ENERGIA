package TeamOne.BW_ENERGIA.services;

import TeamOne.BW_ENERGIA.entities.Ruolo;
import TeamOne.BW_ENERGIA.entities.Utente;
import TeamOne.BW_ENERGIA.repositories.RuoloRepository;
import TeamOne.BW_ENERGIA.repositories.UtenteRepository;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private Cloudinary imgUploader;
    @Autowired
    private RuoloRepository ruoloRepository;

    public Map<Object, Object> findAllMapped() {
        return utenteRepository.findAll().stream()
                .collect(java.util.stream.Collectors.toMap(
                        Utente::getId,
                        utente -> {
                            Map<String, Object> mappa = new java.util.HashMap<>();
                            mappa.put("id", utente.getId());
                            mappa.put("username", utente.getUsername());
                            mappa.put("email", utente.getEmail());
                            mappa.put("password", utente.getPassword());
                            mappa.put("nome", utente.getNome());
                            mappa.put("cognome", utente.getCognome());
                            mappa.put("avatar", utente.getAvatar());
                            return mappa;
                        }
                ));
    }

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public Utente findById(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }

    public Utente save(Utente utente) {
        return utenteRepository.save(utente);
    }

    public void delete(Long id) {
        utenteRepository.deleteById(id);
    }

    public Optional<Utente> findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    public Optional<Utente> findByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return utenteRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return utenteRepository.existsByUsername(username);
    }

    /*public String uploadAvatar(MultipartFile file) {
        try {
            Map result = imgUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageURL = (String) result.get("url");
            return imageURL;
        } catch (Exception e) {
            throw new BadRequestException("Ci sono stati problemi nel salvataggio del file!");
        }
    }*/

    public List<Ruolo> getRuoliByIds(List<Long> ids) {
        return ruoloRepository.findAllById(ids);
    }
}