package it.epicode.build_5.indirizzi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndirizzoRequest {
    private String via;
    private String civico;
    private String localita;
    private String cap;
}
