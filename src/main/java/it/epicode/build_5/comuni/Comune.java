package it.epicode.build_5.comuni;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comuni")

public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "codice_provincia")
    private String codiceProvincia;
    @Column(name = "codice_comune")
    private String codiceComune;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "provincia")
    private Provincia provincia;
}