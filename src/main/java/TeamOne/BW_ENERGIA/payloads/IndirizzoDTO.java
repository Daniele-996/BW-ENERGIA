package TeamOne.BW_ENERGIA.payloads;

import TeamOne.BW_ENERGIA.entities.Comune;

public record IndirizzoDTO(String via, int civico, String localita, Comune comune) {

}
