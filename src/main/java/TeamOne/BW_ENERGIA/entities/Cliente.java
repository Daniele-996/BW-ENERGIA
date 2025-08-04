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
    @Column(name = "ragione_sociale")
    private String ragioneSociale;
    @Column(name = "partita_iva")
    private int partitaIva;
    private String email;
    @Column(name = "data_inserimento")
    private LocalDate dataInserimento;
    @Column(name = "data_ultimo_contatto")
    private LocalDate dataUltimoContatto;
    @Column(name = "fatturato_annuale")
    private int fatturatoAnnuale;
    //@OneToMany
    private List<Fattura> fatture;
    private String pec;
    private int telefono;
    @Column(name = "email_contatto")
    private String emailContatto;
    @Column(name = "nome_contatto")
    private String nomeContatto;
    @Column(name = "cognome_contatto")
    private String cognomeContatto;
    @Column(name = "telefono_contatto")
    private int telefonoContatto;
    @Column(name = "logo_aziendale")
    private String logoAziendale;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @OneToOne
    @Column(name = "indirizzo_sede_operativa")
    private Indirizzo indirizzoSedeOp;
    @OneToOne
    @Column(name = "indirizzo_sede_legale")
    private Indirizzo indirizzoLegale;
}
