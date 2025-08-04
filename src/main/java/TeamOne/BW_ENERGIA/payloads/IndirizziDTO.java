package TeamOne.BW_ENERGIA.payloads;

import TeamOne.BW_ENERGIA.entities.Comune;

public record IndirizziDTO(String via, int civico, String localita, Comune comune) {

}
