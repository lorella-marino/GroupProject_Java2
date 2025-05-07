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
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private int fatturatoAnnuale;
    private String pec;
    private long telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private long telefonoContatto;
    private String logoAziendale;
    @OneToOne (cascade={CascadeType.REMOVE, CascadeType.PERSIST})
    private Indirizzo indirizzoSedeLegale;
    @OneToOne (cascade={CascadeType.REMOVE, CascadeType.PERSIST})
    private Indirizzo indirizzoSedeOperativa;
    @Enumerated (EnumType.STRING)
    private TipoCliente tipoCliente;
    @OneToMany (cascade = CascadeType.PERSIST)
    private List<Fattura> fatture = new ArrayList<>();
}