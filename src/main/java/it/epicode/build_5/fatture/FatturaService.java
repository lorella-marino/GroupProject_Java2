package it.epicode.build_5.fatture;

import it.epicode.build_5.clienti.Cliente;
import it.epicode.build_5.clienti.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public FatturaResponse toResponse(Fattura fattura, Cliente cliente) {
        FatturaResponse fatturaResponse = new FatturaResponse();
        BeanUtils.copyProperties(fattura, fatturaResponse);
        fatturaResponse.setIdCliente(cliente.getId());
        return fatturaResponse;
    }

    public FatturaResponse create(FatturaRequest fatturaRequest, Long idCliente) {
        Fattura fattura = new Fattura();
        BeanUtils.copyProperties(fatturaRequest, fattura);

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente non trovato"));

        fattura.setCliente(cliente);
        cliente.getFatture().add(fattura);

        fatturaRepository.save(fattura);
        clienteRepository.save(cliente);

        return toResponse(fattura, cliente);
    }


    public FatturaResponse update(Long id, FatturaRequest fatturaRequest) {
        Fattura fattura = fatturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fattura non trovata"));

        BeanUtils.copyProperties(fatturaRequest, fattura);
        fatturaRepository.save(fattura);

        Cliente cliente = fattura.getCliente();
        return toResponse(fattura, cliente);
    }


    public void delete(Long id) {
        Fattura fattura = fatturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fattura non trovata"));

        Cliente cliente = fattura.getCliente();
        if (cliente != null) {
            cliente.getFatture().remove(fattura);
            clienteRepository.save(cliente);
        }

        fatturaRepository.delete(fattura);
    }


}
