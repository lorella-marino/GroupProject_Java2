package it.epicode.build_5.fatture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatturaRequest {
    private String data;
    private String importo;
    private String numero;
}
