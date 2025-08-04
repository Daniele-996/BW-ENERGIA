package TeamOne.BW_ENERGIA.entities;

import TeamOne.BW_ENERGIA.enums.Tipo;

import java.time.LocalDate;
import java.util.List;

public class Cliente {
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
