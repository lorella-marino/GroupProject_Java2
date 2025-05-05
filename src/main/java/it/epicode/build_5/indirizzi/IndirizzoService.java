package it.epicode.build_5.indirizzi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;

    public Indirizzo toEntity (IndirizzoRequest indirizzoRequest) {
        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(indirizzoRequest.getVia());
        indirizzo.setCivico(indirizzoRequest.getCivico());
        indirizzo.setLocalita(indirizzoRequest.getLocalita());
        indirizzo.setCap(indirizzoRequest.getCap());
        indirizzo.setComune(null);
        return indirizzo;
    }
}
