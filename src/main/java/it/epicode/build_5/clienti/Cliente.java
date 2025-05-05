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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clienti")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(cascade={CascadeType.REMOVE, CascadeType.PERSIST})
    private AppUser appUser;
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
    @OneToOne (cascade={CascadeType.REMOVE, CascadeType.PERSIST})
    private Indirizzo indirizzoSedeLegale;
    @OneToOne (cascade={CascadeType.REMOVE, CascadeType.PERSIST})
    private Indirizzo indirizzoSedeOperativa;
    @Enumerated (EnumType.STRING)
    private TipoCliente tipoCliente;
    @OneToMany
    private List<Fattura> fatture = new ArrayList<>();
}