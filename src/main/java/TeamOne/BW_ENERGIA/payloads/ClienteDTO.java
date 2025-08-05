package TeamOne.BW_ENERGIA.payloads;

import TeamOne.BW_ENERGIA.enums.Tipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteDTO(
        @NotNull
        String ragioneSociale,

        @NotNull
        int partitaIva,

        @NotNull
        @Email
        String email,

        @NotNull
        LocalDate dataInserimento,

        @NotNull
        LocalDate dataUltimoContatto,

        @NotNull
        int fatturatoAnnuale,

        @NotNull
        @Email
        String pec,

        @NotNull
        int telefono,

        @NotNull
        @Email
        String emailContatto,

        @NotNull
        String nomeContatto,

        @NotNull
        String cognomeContatto,

        @NotNull
        int telefonoContatto,

        @NotNull
        String logoAziendale,

        @NotNull
        Tipo tipo,

        @NotNull
        Long indirizzoSedeOpId,

        @NotNull
        Long indirizzoLegaleId) {
}
