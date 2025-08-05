package TeamOne.BW_ENERGIA.entities;

import TeamOne.BW_ENERGIA.enums.Tipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @NotBlank
    @Column(name = "ragione_sociale")
    private String ragioneSociale;

    @NotBlank
    @Column(name = "partita_iva")
    private int partitaIva;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Column(name = "data_inserimento")
    private LocalDate dataInserimento;

    @NotBlank
    @Column(name = "data_ultimo_contatto")
    private LocalDate dataUltimoContatto;

    @NotBlank
    @Column(name = "fatturato_annuale")
    private int fatturatoAnnuale;

    @NotBlank
    @OneToMany
    private List<Fattura> fatture = new ArrayList<>();

    @NotBlank
    @Email
    private String pec;

    @NotBlank
    private int telefono;

    @NotBlank
    @Email
    @Column(name = "email_contatto")
    private String emailContatto;

    @NotBlank
    @Column(name = "nome_contatto")
    private String nomeContatto;

    @NotBlank
    @Column(name = "cognome_contatto")
    private String cognomeContatto;

    @NotBlank
    @Column(name = "telefono_contatto")
    private int telefonoContatto;

    @NotBlank
    @Column(name = "logo_aziendale")
    private String logoAziendale;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne
    @OneToOne
    @JoinColumn(name = "indirizzo_sede_operativa")
    private Indirizzo indirizzoSedeOp;

    @NotBlank
    @OneToOne
    @JoinColumn(name = "indirizzo_sede_legale")
    private Indirizzo indirizzoLegale;
}
