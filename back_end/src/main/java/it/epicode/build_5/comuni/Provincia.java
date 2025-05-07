package it.epicode.build_5.comuni;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "province")

public class Provincia {
    @Id
    private String sigla;
    private String nome;
    private String regione;
}
