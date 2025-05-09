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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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
        if (fotoProfilo.isEmpty()) {
            throw new IllegalArgumentException("File vuoto. Caricare un'immagine valida.");
        }
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
                cliente.getDataInserimento(),
                cliente.getDataUltimoContatto(),
                cliente.getFatturatoAnnuale(),
                cliente.getPec(),
                cliente.getTelefono(),
                "(" + cliente.getIndirizzoSedeLegale().getComune().getProvincia().getSigla() + ") " + cliente.getIndirizzoSedeLegale().getComune().getNome(),
                "(" + cliente.getIndirizzoSedeOperativa().getComune().getProvincia().getSigla() + ") " + cliente.getIndirizzoSedeOperativa().getComune().getNome(),
                cliente.getTipoCliente());
    }
    
    public List<ClienteResponse> findAll() {
        List<Cliente> clienteList = clienteRepository.findAll();
        return clienteList.stream().map(this::toResponse).toList();
    }
    
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente non trovato"));
    }
    
    public Cliente create(@Valid ClienteFullRequest clienteFullRequest, String comuneSedeLegale, String comuneSedeOperativa, TipoCliente tipoCliente) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteFullRequest.getClienteRequest(), cliente);
        cliente.setTipoCliente(tipoCliente);

        cliente.setDataInserimento(LocalDate.now());
        cliente.setDataUltimoContatto(LocalDate.now());

        Indirizzo indirizzoSedeLegale = indirizzoService.toEntity(clienteFullRequest.getIndirizzoSedeLegale());
        Comune comune1 = comuneRepository.findByNome(comuneSedeLegale);
        if (comune1 == null) throw new IllegalArgumentException("Comune sede legale non trovato");
        indirizzoSedeLegale.setComune(comune1);
        cliente.setIndirizzoSedeLegale(indirizzoSedeLegale);
        
        Indirizzo indirizzoSedeOperativa = indirizzoService.toEntity(clienteFullRequest.getIndirizzoSedeOperativa());
        Comune comune2 = comuneRepository.findByNome(comuneSedeOperativa);
        if (comune2 == null) throw new IllegalArgumentException("Comune sede operativa non trovato");
        indirizzoSedeOperativa.setComune(comune2);
        cliente.setIndirizzoSedeOperativa(indirizzoSedeOperativa);
        
        clienteRepository.save(cliente);
        return cliente;
    }
    
    public Cliente update(Long id, @Valid ClienteRequest clienteRequest) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente non trovato"));
        BeanUtils.copyProperties(clienteRequest, cliente);
        clienteRepository.save(cliente);
        return cliente;
    }
    
    public void delete(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente non trovato"));
        clienteRepository.delete(cliente);
    }
    
    public Page<ClienteResponse> findAllSorted(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return clienteRepository.findAll(pageable).map(this::toResponse);
    }
    
    public List<ClienteResponse> filtraClienti(Integer fatturato, LocalDate inserimento, LocalDate ultimoContatto, String nome) {
        List<Cliente> clienti = clienteRepository.findAll();
        
        return clienti.stream()
                .filter(c -> fatturato == null || c.getFatturatoAnnuale() >= fatturato)
                .filter(c -> inserimento == null || c.getDataInserimento().equals(inserimento))
                .filter(c -> ultimoContatto == null || c.getDataUltimoContatto().equals(ultimoContatto))
                .filter(c -> nome == null || c.getRagioneSociale().toLowerCase().contains(nome.toLowerCase()))
                .map(this::toResponse)
                .toList();
    }
}
