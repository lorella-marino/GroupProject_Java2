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

            ClienteFullRequest cliente4 = new ClienteFullRequest();
            cliente4.setClienteRequest(new ClienteRequest() {{
                setRagioneSociale("Alpha Software SPA");
                setPartitaIva("98765432100");
                setEmail("info@alphasoft.it");
                setPec("alphasoft@pec.it");
                setTelefono("0267891234");
                setEmailContatto("giulia.bianchi@alphasoft.it");
                setNomeContatto("Giulia");
                setCognomeContatto("Bianchi");
                setTelefonoContatto("3487654321");
                setFatturatoAnnuale(320000);
                setDataInserimento(LocalDate.of(2021, 11, 5));
            }});
            cliente4.setIndirizzoSedeLegale(new IndirizzoRequest() {{
                setVia("Via Milano");
                setCivico("22");
                setLocalita("Milano");
                setCap("20100");
            }});
            cliente4.setIndirizzoSedeOperativa(new IndirizzoRequest() {{
                setVia("Viale Lombardia");
                setCivico("3");
                setLocalita("Monza");
                setCap("20900");
            }});

            ClienteFullRequest cliente5 = new ClienteFullRequest();
            cliente5.setClienteRequest(new ClienteRequest() {{
                setRagioneSociale("Beta Consulting SAS");
                setPartitaIva("11223344556");
                setEmail("info@betaconsult.it");
                setPec("betaconsult@pec.it");
                setTelefono("0451236789");
                setEmailContatto("luca.verdi@betaconsult.it");
                setNomeContatto("Luca");
                setCognomeContatto("Verdi");
                setTelefonoContatto("3471122445");
                setFatturatoAnnuale(180000);
                setDataInserimento(LocalDate.of(2020, 6, 30));
            }});
            cliente5.setIndirizzoSedeLegale(new IndirizzoRequest() {{
                setVia("Via Roma");
                setCivico("18");
                setLocalita("Verona");
                setCap("37100");
            }});
            cliente5.setIndirizzoSedeOperativa(new IndirizzoRequest() {{
                setVia("Via Mantova");
                setCivico("45");
                setLocalita("Bussolengo");
                setCap("37012");
            }});

            ClienteFullRequest cliente6 = new ClienteFullRequest();
            cliente6.setClienteRequest(new ClienteRequest() {{
                setRagioneSociale("Gamma Logistics SRL");
                setPartitaIva("22334455667");
                setEmail("info@gammalog.it");
                setPec("gammalog@pec.it");
                setTelefono("0517893210");
                setEmailContatto("marco.neri@gammalog.it");
                setNomeContatto("Marco");
                setCognomeContatto("Neri");
                setTelefonoContatto("3469876543");
                setFatturatoAnnuale(400000);
                setDataInserimento(LocalDate.of(2023, 3, 1));
            }});
            cliente6.setIndirizzoSedeLegale(new IndirizzoRequest() {{
                setVia("Via Bologna");
                setCivico("12");
                setLocalita("Bologna");
                setCap("40100");
            }});
            cliente6.setIndirizzoSedeOperativa(new IndirizzoRequest() {{
                setVia("Via Emilia");
                setCivico("80");
                setLocalita("Imola");
                setCap("40026");
            }});

            ClienteFullRequest cliente7 = new ClienteFullRequest();
            cliente7.setClienteRequest(new ClienteRequest() {{
                setRagioneSociale("Delta Engineering SPA");
                setPartitaIva("33445566778");
                setEmail("info@deltaeng.it");
                setPec("deltaeng@pec.it");
                setTelefono("0836123456");
                setEmailContatto("federica.russo@deltaeng.it");
                setNomeContatto("Federica");
                setCognomeContatto("Russo");
                setTelefonoContatto("3493322110");
                setFatturatoAnnuale(500000);
                setDataInserimento(LocalDate.of(2019, 12, 10));
            }});
            cliente7.setIndirizzoSedeLegale(new IndirizzoRequest() {{
                setVia("Via Lecce");
                setCivico("5");
                setLocalita("Lecce");
                setCap("73100");
            }});
            cliente7.setIndirizzoSedeOperativa(new IndirizzoRequest() {{
                setVia("Via Taranto");
                setCivico("25");
                setLocalita("Brindisi");
                setCap("72100");
            }});

            ClienteFullRequest cliente8 = new ClienteFullRequest();
            cliente8.setClienteRequest(new ClienteRequest() {{
                setRagioneSociale("Epsilon Tech SRL");
                setPartitaIva("44556677889");
                setEmail("contact@epsilontech.it");
                setPec("epsilontech@pec.it");
                setTelefono("0165321987");
                setEmailContatto("alessio.ferri@epsilontech.it");
                setNomeContatto("Alessio");
                setCognomeContatto("Ferri");
                setTelefonoContatto("3452233445");
                setFatturatoAnnuale(270000);
                setDataInserimento(LocalDate.of(2021, 9, 20));
            }});
            cliente8.setIndirizzoSedeLegale(new IndirizzoRequest() {{
                setVia("Via Ivrea");
                setCivico("6");
                setLocalita("Aosta");
                setCap("11100");
            }});
            cliente8.setIndirizzoSedeOperativa(new IndirizzoRequest() {{
                setVia("Via Chatillon");
                setCivico("22");
                setLocalita("Aosta");
                setCap("11100");
            }});

            ClienteFullRequest cliente9 = new ClienteFullRequest();
            cliente9.setClienteRequest(new ClienteRequest() {{
                setRagioneSociale("Zeta Marketing SAS");
                setPartitaIva("55667788990");
                setEmail("hello@zetamarketing.it");
                setPec("zetamarketing@pec.it");
                setTelefono("0701234567");
                setEmailContatto("serena.gallo@zetamarketing.it");
                setNomeContatto("Serena");
                setCognomeContatto("Gallo");
                setTelefonoContatto("3499988776");
                setFatturatoAnnuale(220000);
                setDataInserimento(LocalDate.of(2022, 1, 8));
            }});
            cliente9.setIndirizzoSedeLegale(new IndirizzoRequest() {{
                setVia("Via Cagliari");
                setCivico("33");
                setLocalita("Cagliari");
                setCap("09100");
            }});
            cliente9.setIndirizzoSedeOperativa(new IndirizzoRequest() {{
                setVia("Viale Poetto");
                setCivico("5");
                setLocalita("Quartu Sant'Elena");
                setCap("09045");
            }});

            ClienteFullRequest cliente10 = new ClienteFullRequest();
            cliente10.setClienteRequest(new ClienteRequest() {{
                setRagioneSociale("Omega Edilizia SRL");
                setPartitaIva("66778899001");
                setEmail("info@omegaedil.it");
                setPec("omegaedil@pec.it");
                setTelefono("0423987456");
                setEmailContatto("daniele.martini@omegaedil.it");
                setNomeContatto("Daniele");
                setCognomeContatto("Martini");
                setTelefonoContatto("3421112233");
                setFatturatoAnnuale(350000);
                setDataInserimento(LocalDate.of(2023, 5, 15));
            }});
            cliente10.setIndirizzoSedeLegale(new IndirizzoRequest() {{
                setVia("Via Venezia");
                setCivico("15");
                setLocalita("Treviso");
                setCap("31100");
            }});
            cliente10.setIndirizzoSedeOperativa(new IndirizzoRequest() {{
                setVia("Via Castellana");
                setCivico("60");
                setLocalita("Mestre");
                setCap("30174");
            }});

            ClienteFullRequest cliente11 = new ClienteFullRequest();
            cliente11.setClienteRequest(new ClienteRequest() {{
                setRagioneSociale("Sigma Energia SPA");
                setPartitaIva("77889900112");
                setEmail("energia@sigma.it");
                setPec("sigmaenergia@pec.it");
                setTelefono("0556789123");
                setEmailContatto("ilaria.fontana@sigma.it");
                setNomeContatto("Ilaria");
                setCognomeContatto("Fontana");
                setTelefonoContatto("3481122993");
                setFatturatoAnnuale(620000);
                setDataInserimento(LocalDate.of(2022, 7, 25));
            }});
            cliente11.setIndirizzoSedeLegale(new IndirizzoRequest() {{
                setVia("Via Firenze");
                setCivico("44");
                setLocalita("Firenze");
                setCap("50100");
            }});
            cliente11.setIndirizzoSedeOperativa(new IndirizzoRequest() {{
                setVia("Viale Europa");
                setCivico("12");
                setLocalita("Prato");
                setCap("59100");
            }});

            clienteService.create(cliente1, "Torino", "Torino", TipoCliente.SRL);
            clienteService.create(cliente2, "Bologna", "Bologna", TipoCliente.SPA);
            clienteService.create(cliente3, "Milano", "Milano", TipoCliente.SAS);
            clienteService.create(cliente4, "Milano", "Milano", TipoCliente.SPA);
            clienteService.create(cliente5, "Verona", "Pavia", TipoCliente.SAS);
            clienteService.create(cliente6, "Bologna", "Torino", TipoCliente.SRL);
            clienteService.create(cliente7, "Milano", "Milano", TipoCliente.SPA);
            clienteService.create(cliente8, "Torino", "Bologna", TipoCliente.SRL);
            clienteService.create(cliente9, "Torino", "Torino", TipoCliente.SAS);
            clienteService.create(cliente10, "Verona", "Verona", TipoCliente.SRL);
            clienteService.create(cliente11, "Verona", "Verona", TipoCliente.SPA);
        }
    }
}

