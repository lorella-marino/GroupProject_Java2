package it.epicode.build_5.clienti;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c JOIN c.indirizzoSedeLegale i JOIN i.comune co ORDER BY co.provincia ASC")
    Page<Cliente> orderByProvinciaSedeLegaleAsc(Pageable pageable);
    
    @Query("SELECT c FROM Cliente c JOIN c.indirizzoSedeLegale i JOIN i.comune co ORDER BY co.provincia DESC")
    Page<Cliente> orderByProvinciaSedeLegaleDesc(Pageable pageable);
    
    List<Cliente> findByFatturatoAnnuale(String fatturatoAnnuale);
    
    List<Cliente> findByDataInserimento(String dataInserimento);
    
    List<Cliente> findByDataUltimoContatto(String dataUltimoContatto);
    
    List<Cliente> findByRagioneSocialeContainingIgnoreCase(String nomeParziale);
}