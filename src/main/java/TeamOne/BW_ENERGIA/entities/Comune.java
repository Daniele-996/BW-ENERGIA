package TeamOne.BW_ENERGIA.entities;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
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

    @Column(name = "progressivo_comune")
    @CsvBindByName(column = "Progressivo del comune")
    private int progressivoComune;

    @CsvBindByName(column = "Denominazione in italiano")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    @CsvBindByName(column = "Codice provincia")
    private Provincia provincia;
}
