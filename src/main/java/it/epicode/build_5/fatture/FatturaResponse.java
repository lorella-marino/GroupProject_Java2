package it.epicode.build_5.fatture;

import it.epicode.build_5.clienti.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatturaResponse {
    private Long id;
    private Long idCliente;
    private String data;
    private String importo;
    private String numero;
}
