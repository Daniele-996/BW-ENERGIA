package TeamOne.BW_ENERGIA.entities;

import TeamOne.BW_ENERGIA.enums.Tipo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String ragioneSociale;
    private int partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private int fatturatoAnnuale;
    private List<Fattura> fatture;
    private String pec;
    private int telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private int telefonoContatto;
    private String logoAziendale;
    private Tipo tipo;
    private Indirizzo indirizzoSedeOp;
    private Indirizzo indirizzoLegale;
}
