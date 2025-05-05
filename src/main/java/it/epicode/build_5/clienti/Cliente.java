package it.epicode.build_5.clienti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clienti")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private String dataInserimento;
    private String dataUltimoContatto;
    private String fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;
    private String indirizzoSedeLegale;
    private String indirizzoSedeOperativa;
    @Enumerated (EnumType.STRING)
    private TipoCliente tipoCliente;
}