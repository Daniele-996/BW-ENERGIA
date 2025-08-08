package TeamOne.BW_ENERGIA.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UtenteDTO(
        @NotNull(message = "Lo username è obbligatorio")
        @Size(min = 3, max = 20, message = "Lo username deve avere tra 3 e 20 caratteri")
        String username,

        @Email(message = "Email non valida")
        @NotNull(message = "L'email è obbligatoria")
        String email,

        @NotNull(message = "La password è obbligatoria")
        @Size(min = 6, message = "La password deve contenere almeno 6 caratteri")
        String password,

        @NotNull(message = "Il nome è obbligatorio")
        String nome,

        @NotNull(message = "Il cognome è obbligatorio")
        String cognome

) {
}
