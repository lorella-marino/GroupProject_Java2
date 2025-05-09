package it.epicode.build_5.indirizzi;

import it.epicode.build_5.comuni.Comune;
import it.epicode.build_5.comuni.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    
    @Autowired
    private ComuneRepository comuneRepository;

    public Indirizzo toEntity (IndirizzoRequest indirizzoRequest) {
        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(indirizzoRequest.getVia());
        indirizzo.setCivico(indirizzoRequest.getCivico());
        indirizzo.setLocalita(indirizzoRequest.getLocalita());
        indirizzo.setCap(indirizzoRequest.getCap());
        indirizzo.setComune(null);
        return indirizzo;
    }
    
    public Indirizzo creaIndirizzo(String via, String civico, String comuneNome, String cap) {
        Comune comune = comuneRepository.findByNome(comuneNome);
        if (comune == null) {
            throw new IllegalArgumentException("Comune non trovato: " + comuneNome);
        }
        
        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(via);
        indirizzo.setCivico(civico);
        indirizzo.setCap(cap);
        indirizzo.setLocalita(comune.getNome());
        indirizzo.setComune(comune);
        
        return indirizzoRepository.save(indirizzo);
    }
}
