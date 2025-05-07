package it.epicode.build_5.clienti;

import it.epicode.build_5.indirizzi.Indirizzo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private int fatturatoAnnuale;
    private String pec;
    private long telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private long telefonoContatto;
}
