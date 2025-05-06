package it.epicode.build_5.fatture;

import it.epicode.build_5.clienti.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatturaResponse {
    private Long id;
    private Long idCliente;
    private LocalDate data;
    private int importo;
    private String numero;
    private StatoFattura stato;
}
