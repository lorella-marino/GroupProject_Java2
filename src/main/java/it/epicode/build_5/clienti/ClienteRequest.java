package it.epicode.build_5.clienti;

import it.epicode.build_5.indirizzi.Indirizzo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class ClienteRequest {
    @NotBlank
    private String ragioneSociale;
    @NotBlank
    private String partitaIva;
    @Email
    @NotBlank
    private String email;
    @PastOrPresent
    private LocalDate dataInserimento;
    @PastOrPresent
    private LocalDate dataUltimoContatto;
    @Min(0)
    private int fatturatoAnnuale;
    @NotBlank
    private String pec;
    @NotBlank
    private String telefono;
    @Email
    private String emailContatto;
    @NotBlank
    private String nomeContatto;
    @NotBlank
    private String cognomeContatto;
    @NotBlank
    private String telefonoContatto;
}

