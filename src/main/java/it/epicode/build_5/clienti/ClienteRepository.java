package it.epicode.build_5.clienti;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c JOIN c.indirizzoSedeLegale i JOIN i.comune co ORDER BY co.provincia ASC")
    Page<Cliente> orderByProvinciaSedeLegaleAsc(Pageable pageable);
    
    @Query("SELECT c FROM Cliente c JOIN c.indirizzoSedeLegale i JOIN i.comune co ORDER BY co.provincia DESC")
    Page<Cliente> orderByProvinciaSedeLegaleDesc(Pageable pageable);

    @Query("""
    SELECT c FROM Cliente c
    WHERE (:fatturatoMinimo IS NULL OR c.fatturatoAnnuale >= :fatturatoMinimo)
    AND (:annoInserimento IS NULL OR FUNCTION('YEAR', c.dataInserimento) = :annoInserimento)
    AND (:annoUltimoContatto IS NULL OR FUNCTION('YEAR', c.dataUltimoContatto) = :annoUltimoContatto)
    AND (:nome IS NULL OR LOWER(c.ragioneSociale) LIKE LOWER(CONCAT('%', :nome, '%')))
    """)
    Page<Cliente> filtraClientiAvanzato(
            @Param("fatturatoMinimo") Integer fatturatoMinimo,
            @Param("annoInserimento") Integer annoInserimento,
            @Param("annoUltimoContatto") Integer annoUltimoContatto,
            @Param("nome") String nome,
            Pageable pageable);
    
    List<Cliente> findByFatturatoAnnuale(Integer fatturatoAnnuale);
    
    List<Cliente> findByDataInserimento(LocalDate dataInserimento);
    
    List<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto);
    
    List<Cliente> findByRagioneSocialeContainingIgnoreCase(String nomeParziale);
}