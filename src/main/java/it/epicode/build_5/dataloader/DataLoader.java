package it.epicode.build_5.dataloader;

import it.epicode.build_5.clienti.*;
import it.epicode.build_5.indirizzi.Indirizzo;
import it.epicode.build_5.indirizzi.IndirizzoRequest;
import it.epicode.build_5.indirizzi.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private IndirizzoService indirizzoService;

    @Autowired
    private ClienteService clienteService;
    
    @Override
    public void run(String... args) throws Exception {
        if (clienteRepository.count() == 0) {

            ClienteFullRequest cliente1 = new ClienteFullRequest();
            cliente1.setClienteRequest(new ClienteRequest() {{
                setRagioneSociale("Tech Solutions SRL");
                setPartitaIva("12345678901");
                setEmail("info@techsolutions.it");
                setPec("techsolutions@pec.it");
                setTelefono("0111234567");
                setEmailContatto("mario.rossi@techsolutions.it");
                setNomeContatto("Mario");
                setCognomeContatto("Rossi");
                setTelefonoContatto("3391122334");
                setFatturatoAnnuale(250000);
                setDataInserimento(LocalDate.of(2022, 2, 15));
            }});
            cliente1.setIndirizzoSedeLegale(new IndirizzoRequest() {{
                setVia("Via Torino");
                setCivico("10");
                setLocalita("Torino");
                setCap("10100");
            }});
            cliente1.setIndirizzoSedeOperativa(new IndirizzoRequest() {{
                setVia("Corso Francia");
                setCivico("50");
                setLocalita("Rivoli");
                setCap("10098");
            }});

            ClienteFullRequest cliente2 = new ClienteFullRequest();
            cliente2.setClienteRequest(new ClienteRequest() {{
                setRagioneSociale("GreenFarm SPA");
                setPartitaIva("98765432109");
                setEmail("contatti@greenfarm.it");
                setPec("greenfarm@pec.it");
                setTelefono("0517654321");
                setEmailContatto("luca.verdi@greenfarm.it");
                setNomeContatto("Luca");
                setCognomeContatto("Verdi");
                setTelefonoContatto("3489988776");
                setFatturatoAnnuale(500000);
                setDataInserimento(LocalDate.of(2021, 12, 10));
            }});
            cliente2.setIndirizzoSedeLegale(new IndirizzoRequest() {{
                setVia("Via Emilia");
                setCivico("123");
                setLocalita("Bologna");
                setCap("40100");
            }});
            cliente2.setIndirizzoSedeOperativa(new IndirizzoRequest() {{
                setVia("Via dei Fiori");
                setCivico("7");
                setLocalita("Imola");
                setCap("40026");
            }});

            ClienteFullRequest cliente3 = new ClienteFullRequest();
            cliente3.setClienteRequest(new ClienteRequest() {{
                setRagioneSociale("Studio Legale Bianchi");
                setPartitaIva("56789123450");
                setEmail("info@studiobianchi.it");
                setPec("studiobianchi@pec.it");
                setTelefono("0667788990");
                setEmailContatto("giulia.bianchi@studiobianchi.it");
                setNomeContatto("Giulia");
                setCognomeContatto("Bianchi");
                setTelefonoContatto("3281122333");
                setFatturatoAnnuale(120000);
                setDataInserimento(LocalDate.of(2025, 04, 10));
            }});
            cliente3.setIndirizzoSedeLegale(new IndirizzoRequest() {{
                setVia("Via Nazionale");
                setCivico("25");
                setLocalita("Roma");
                setCap("00184");
            }});
            cliente3.setIndirizzoSedeOperativa(new IndirizzoRequest() {{
                setVia("Via del Corso");
                setCivico("99");
                setLocalita("Roma");
                setCap("00186");
            }});

            clienteService.create(cliente1, "Torino", "Rivoli", TipoCliente.SRL);
            clienteService.create(cliente2, "Bologna", "Imola", TipoCliente.SPA);
            clienteService.create(cliente3, "Roma", "Roma", TipoCliente.SAS);
        }
    }
}

