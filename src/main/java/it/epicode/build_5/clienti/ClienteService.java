package it.epicode.build_5.clienti;

import it.epicode.build_5.cloudinary.CloudinaryService;
import it.epicode.build_5.comuni.Comune;
import it.epicode.build_5.comuni.ComuneRepository;
import it.epicode.build_5.indirizzi.Indirizzo;
import it.epicode.build_5.indirizzi.IndirizzoRequest;
import it.epicode.build_5.indirizzi.IndirizzoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Validated
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ComuneRepository comuneRepository;
    @Autowired
    private IndirizzoService indirizzoService;

    public void uploadLogoAziendale(Long id, MultipartFile fotoProfilo) {
        Cliente dipendente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente non trovato"));
        String url = cloudinaryService.uploadImage(fotoProfilo);
        dipendente.setLogoAziendale(url);
        clienteRepository.save(dipendente);
    }

    public ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getRagioneSociale(),
                cliente.getPartitaIva(),
                cliente.getEmail(),
                cliente.getFatturatoAnnuale(),
                cliente.getPec(),
                cliente.getTelefono(),
                cliente.getIndirizzoSedeLegale().getVia()+cliente.getIndirizzoSedeLegale().getComune(),
                cliente.getIndirizzoSedeOperativa().getVia()+cliente.getIndirizzoSedeOperativa().getComune(),
                cliente.getTipoCliente());
    }

    public List<ClienteResponse> findAll() {
        List <Cliente> clienteList = clienteRepository.findAll();
        return clienteList.stream().map(this::toResponse).toList();
    }

    public Cliente findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Cliente non trovato"));
        return cliente;
    }

    public Cliente create(@Valid ClienteRequest clienteRequest, TipoCliente tipoCliente, IndirizzoRequest indirizzoSedeLegale, IndirizzoRequest indirizzoSedeOperativa, String comune) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteRequest, cliente);
        cliente.setTipoCliente(tipoCliente);

        Indirizzo indirizzoSedeLegale1 = indirizzoService.toEntity(indirizzoSedeLegale);
        cliente.setIndirizzoSedeLegale(indirizzoSedeLegale1);
        Comune comune1 = comuneRepository.findByNome(comune);
        cliente.getIndirizzoSedeLegale().setComune(comune1);

        Indirizzo indirizzoSedeOperativa1 = indirizzoService.toEntity(indirizzoSedeOperativa);
        cliente.setIndirizzoSedeOperativa(indirizzoSedeOperativa1);
        cliente.getIndirizzoSedeOperativa().setComune(comune1);

        clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente update(Long id, @Valid ClienteRequest clienteRequest) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Cliente non trovato"));
        BeanUtils.copyProperties(clienteRequest, cliente);
        clienteRepository.save(cliente);
       return cliente;
    }

    public void delete(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Cliente non trovato"));
        clienteRepository.delete(cliente);
    }
}
