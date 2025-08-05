package TeamOne.BW_ENERGIA.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "comuni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @NotBlank
    @Column(name = "progressivo_comune")
    private int progressivoComune;

    @NotBlank
    private String nome;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;
}
