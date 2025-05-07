package it.epicode.build_5.indirizzi;

import it.epicode.build_5.comuni.Comune;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "indirizzi")

public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String via;
    private String civico;
    private String localita;
    private String cap;
    @ManyToOne
    private Comune comune;
}