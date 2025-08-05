package TeamOne.BW_ENERGIA.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotNull String username,
        @Email @NotNull String email,
        @NotNull @Size(min = 8) String password,
        @NotNull String nome,
        @NotNull String cognome
) {
}
