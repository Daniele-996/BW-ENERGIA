package TeamOne.BW_ENERGIA.payloads;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "Username  obbligatorio!")
        String email,
        @NotBlank(message = "Password obbligatoria!")
        String password
) {}
