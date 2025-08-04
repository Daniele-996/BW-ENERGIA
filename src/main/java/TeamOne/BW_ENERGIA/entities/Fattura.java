package TeamOne.BW_ENERGIA.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "fatture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private LocalDate data;
    private int importo;
    private int numero;
    @ManyToOne
    @JoinColumn(name = "stati_fatture")
    private StatoFattura statoFattura;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
