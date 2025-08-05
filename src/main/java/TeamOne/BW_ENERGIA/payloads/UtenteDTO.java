package TeamOne.BW_ENERGIA.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UtenteDTO(
        @NotBlank(message = "Lo username è obbligatorio")
        @Size(min = 3, max = 20, message = "Lo username deve avere tra 3 e 20 caratteri")
        String username,

        @Email(message = "Email non valida")
        @NotBlank(message = "L'email è obbligatoria")
        String email,

        @NotBlank(message = "La password è obbligatoria")
        @Size(min = 6, message = "La password deve contenere almeno 6 caratteri")
        String password,

        @NotBlank(message = "Il nome è obbligatorio")
        String nome,

        @NotBlank(message = "Il cognome è obbligatorio")
        String cognome

        //   String avatar,

        //  List<Ruolo> idRuoli
) {
}
