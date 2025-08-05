package TeamOne.BW_ENERGIA.payloads;

import jakarta.validation.constraints.NotBlank;

public record ProvinciaDTO(
        Long id,

        @NotBlank
        String sigla,

        @NotBlank
        String nome,

        @NotBlank
        String regione
) {
}