package TeamOne.BW_ENERGIA.payloads;

import jakarta.validation.constraints.NotNull;

public record ProvinciaDTO(
        Long id,

        @NotNull
        String sigla,

        @NotNull
        String nome,

        @NotNull
        String regione
) {
}