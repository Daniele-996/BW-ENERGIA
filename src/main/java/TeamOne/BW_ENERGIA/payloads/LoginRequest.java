package TeamOne.BW_ENERGIA.payloads;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull String username,
        @NotNull String password
) {
}
