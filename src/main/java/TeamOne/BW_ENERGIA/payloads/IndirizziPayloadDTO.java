package TeamOne.BW_ENERGIA.payloads;

import TeamOne.BW_ENERGIA.entities.Comune;

public record IndirizziPayloadDTO(String via, int civico, String localita, Comune comune) {

}
