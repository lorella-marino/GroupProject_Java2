package it.epicode.build_5.fatture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatturaRequest {
    private LocalDate data;
    private int importo;
    private String numero;
    private StatoFattura stato;
}
