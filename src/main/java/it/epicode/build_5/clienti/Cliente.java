package it.epicode.build_5.clienti;

import it.epicode.build_5.auth.app_user.AppUser;
import it.epicode.build_5.fatture.Fattura;
import it.epicode.build_5.indirizzi.Indirizzo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @Column(name = "ragione_sociale")
    private String ragioneSociale;
    @Column(name = "partita_iva")
    private String partitaIva;
    private String email;
    @Column(name = "data_inserimento")
    private LocalDate dataInserimento;
    @Column(name = "data_ultimo_contatto")
    private LocalDate dataUltimoContatto;
    @Column(name = "fatturato_annuale")
    private int fatturatoAnnuale;
    private String pec;
    private String telefono;
    @Column(name = "email_contatto")
    private String emailContatto;
    @Column(name = "nome_contatto")
    private String nomeContatto;
    @Column(name = "cognome_contatto")
    private String cognomeContatto;
    @Column(name = "telefono_contatto")
    private String telefonoContatto;
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