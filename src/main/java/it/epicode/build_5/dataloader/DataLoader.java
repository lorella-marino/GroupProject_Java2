package it.epicode.build_5.dataloader;

import it.epicode.build_5.clienti.Cliente;
import it.epicode.build_5.clienti.ClienteRepository;
import it.epicode.build_5.clienti.TipoCliente;
import it.epicode.build_5.indirizzi.Indirizzo;
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
    
    @Override
    public void run(String... args) throws Exception {
        if (clienteRepository.count() == 0) {
            
            Cliente cliente1 = new Cliente();
            cliente1.setRagioneSociale("TechCorp SRL");
            cliente1.setPartitaIva("12345678901");
            cliente1.setEmail("info@techcorp.it");
            cliente1.setDataInserimento(LocalDate.of(2023, 1, 10));
            cliente1.setDataUltimoContatto(LocalDate.of(2024, 5, 1));
            cliente1.setFatturatoAnnuale(500000);
            cliente1.setPec("techcorp@pec.it");
            cliente1.setTelefono("0687654321");
            cliente1.setEmailContatto("g.verdi@techcorp.it");
            cliente1.setNomeContatto("Giulia");
            cliente1.setCognomeContatto("Verdi");
            cliente1.setTelefonoContatto("3299988776");
            cliente1.setTipoCliente(TipoCliente.SRL);
            
            Indirizzo legale1 = indirizzoService.creaIndirizzo("Via Roma", "10", "Roma", "00100");
            Indirizzo operativa1 = indirizzoService.creaIndirizzo("Via Milano", "22", "Milano", "20100");
            cliente1.setIndirizzoSedeLegale(legale1);
            cliente1.setIndirizzoSedeOperativa(operativa1);
            
            clienteRepository.save(cliente1);
            
            Cliente cliente2 = new Cliente();
            cliente2.setRagioneSociale("GreenFarm SAS");
            cliente2.setPartitaIva("98765432109");
            cliente2.setEmail("contatti@greenfarm.it");
            cliente2.setDataInserimento(LocalDate.of(2022, 7, 10));
            cliente2.setDataUltimoContatto(LocalDate.of(2025, 1, 20));
            cliente2.setFatturatoAnnuale(320000);
            cliente2.setPec("greenfarm@pec.it");
            cliente2.setTelefono("0774123456");
            cliente2.setEmailContatto("l.bianchi@greenfarm.it");
            cliente2.setNomeContatto("Luca");
            cliente2.setCognomeContatto("Bianchi");
            cliente2.setTelefonoContatto("3319988776");
            cliente2.setTipoCliente(TipoCliente.SAS);
            
            Indirizzo legale2 = indirizzoService.creaIndirizzo("Strada Provinciale 23", "5", "Pavia", "27100");
            Indirizzo operativa2 = indirizzoService.creaIndirizzo("Via delle Vigne", "9", "Lodi", "26900");
            cliente2.setIndirizzoSedeLegale(legale2);
            cliente2.setIndirizzoSedeOperativa(operativa2);
            
            clienteRepository.save(cliente2);
        }
    }
}

