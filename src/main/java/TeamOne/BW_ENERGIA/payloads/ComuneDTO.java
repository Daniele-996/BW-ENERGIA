package TeamOne.BW_ENERGIA.payloads;

import jakarta.validation.constraints.NotBlank;

public record ComuneDTO(
        @NotBlank
        String nome,

        @NotBlank
        Long provinciaId
) {
}
