package TeamOne.BW_ENERGIA.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FatturaDTO(
        @NotNull(message = "La data della fattura è obbligatoria")
        LocalDate data,
        @Min(value = 1, message = "L'importo deve essere maggiore di 0!")
        int importo,
        @Min(value = 1, message = "Inserisci il numero fattura ovviamente diverso da 0")
        int numero,
        @NotNull(message = "Lo stato della fattura è obbligatorio!")
        Long statoFatturaId,
        @NotNull(message = "Il cliento è obbligatorio!")
        Long clienteId
) {
}
