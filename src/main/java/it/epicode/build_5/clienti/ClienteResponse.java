package it.epicode.build_5.clienti;

import it.epicode.build_5.auth.app_user.AppUser;
import it.epicode.build_5.fatture.Fattura;
import it.epicode.build_5.indirizzi.Indirizzo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String dataInserimento;
    private String dataUltimoContatto;
    private String fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String indirizzoSedeLegale;
    private String indirizzoSedeOperativa;
    private TipoCliente tipoCliente;
}
