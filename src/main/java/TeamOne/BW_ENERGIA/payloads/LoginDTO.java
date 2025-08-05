package TeamOne.BW_ENERGIA.payloads;

import jakarta.validation.constraints.NotNull;

public record LoginDTO(
        @NotNull(message = "Username  obbligatorio!")
        String email,
        @NotNull(message = "Password obbligatoria!")
        String password
) {
}
