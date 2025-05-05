package it.epicode.build_5.clienti;

import it.epicode.build_5.indirizzi.IndirizzoRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteFullRequest {
    private ClienteRequest clienteRequest;
    private IndirizzoRequest indirizzoSedeLegale;
    private IndirizzoRequest indirizzoSedeOperativa;
}
