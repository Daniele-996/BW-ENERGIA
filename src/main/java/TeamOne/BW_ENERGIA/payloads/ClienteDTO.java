package TeamOne.BW_ENERGIA.payloads;

import TeamOne.BW_ENERGIA.enums.Tipo;
import java.time.LocalDate;

public class ClienteDTO {
    private String ragioneSociale;
    private int partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private int fatturatoAnnuale;
    private String pec;
    private int telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private int telefonoContatto;
    private String logoAziendale;
    private Tipo tipo;
    private Long indirizzoSedeOpId;
    private Long indirizzoLegaleId;
    
}
