package TeamOne.BW_ENERGIA.payloads;

import TeamOne.BW_ENERGIA.enums.Tipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ClienteDTO(
        @NotBlank
        String ragioneSociale,

        @NotBlank
        int partitaIva,

        @NotBlank
        @Email
        String email,

        @NotBlank
        LocalDate dataInserimento,

        @NotBlank
        LocalDate dataUltimoContatto,

        @NotBlank
        int fatturatoAnnuale,

        @NotBlank
        @Email
        String pec,

        @NotBlank
        int telefono,

        @NotBlank
        @Email
        String emailContatto,

        @NotBlank
        String nomeContatto,

        @NotBlank
        String cognomeContatto,

        @NotBlank
        int telefonoContatto,

        @NotBlank
        String logoAziendale,

        @NotBlank
        Tipo tipo,

        @NotBlank
        Long indirizzoSedeOpId,

        @NotBlank
        Long indirizzoLegaleId) {
}
