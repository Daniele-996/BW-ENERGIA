package TeamOne.BW_ENERGIA.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private LocalDate data;

    @NotBlank
    private int importo;

    @NotBlank
    private int numero;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "stati_fatture")
    private StatoFattura statoFattura;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;
}
