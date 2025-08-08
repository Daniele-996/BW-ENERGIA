package TeamOne.BW_ENERGIA.payloads;

import jakarta.validation.constraints.NotNull;

public record ComuneDTO(
        @NotNull
        String nome,

        @NotNull
        Long provinciaId
) {
}
