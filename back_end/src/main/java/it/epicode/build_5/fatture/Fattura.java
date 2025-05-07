package it.epicode.build_5.fatture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.build_5.clienti.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fatture")

public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int importo;
    private String numero;
    @ManyToOne
    @JsonIgnore
    private Cliente cliente;
    
    @Enumerated(EnumType.STRING)
    private StatoFattura stato;
    
    private LocalDate data;
    
}