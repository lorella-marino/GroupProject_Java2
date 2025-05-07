package it.epicode.build_5.clienti;

import it.epicode.build_5.auth.app_user.AppUser;
import it.epicode.build_5.fatture.Fattura;
import it.epicode.build_5.indirizzi.Indirizzo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private Long id;
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private int fatturatoAnnuale;
    private String pec;
    private long telefono;
    private String indirizzoSedeLegale;
    private String indirizzoSedeOperativa;
    private TipoCliente tipoCliente;
}
