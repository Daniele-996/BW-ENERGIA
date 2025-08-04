package TeamOne.BW_ENERGIA.payloads;

import TeamOne.BW_ENERGIA.enums.Tipo;

import java.time.LocalDate;

public record ClienteDTO(String ragioneSociale,
                         int partitaIva,
                         String email,
                         LocalDate dataInserimento,
                         LocalDate dataUltimoContatto,
                         int fatturatoAnnuale,
                         String pec,
                         int telefono,
                         String emailContatto,
                         String nomeContatto,
                         String cognomeContatto,
                         int telefonoContatto,
                         String logoAziendale,
                         Tipo tipo,
                         Long indirizzoSedeOpId,
                         Long indirizzoLegaleId) {
}
