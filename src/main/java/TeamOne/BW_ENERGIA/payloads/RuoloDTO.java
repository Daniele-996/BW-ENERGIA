package TeamOne.BW_ENERGIA.payloads;

import TeamOne.BW_ENERGIA.entities.Utente;

import java.util.List;

public record RuoloDTO(String ruolo, List<Utente> utenti) {
}
