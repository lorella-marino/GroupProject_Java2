package it.epicode.build_5.clienti;

import it.epicode.build_5.cloudinary.CloudinaryService;
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
                cliente.getAppUser(),
                cliente.getRagioneSociale(),
                cliente.getPartitaIva(),
                cliente.getEmail(),
                cliente.getDataInserimento(),
                cliente.getDataUltimoContatto(),
                cliente.getFatturatoAnnuale(),
                cliente.getPec(),
                cliente.getTelefono(),
                cliente.getEmailContatto(),
                cliente.getNomeContatto(),
                cliente.getCognomeContatto(),
                cliente.getTelefonoContatto(),
                cliente.getLogoAziendale(),
                cliente.getIndirizzoSedeLegale(),
                cliente.getIndirizzoSedeOperativa(),
                cliente.getTipoCliente(),
                cliente.getFatture());
    }

    public List<ClienteResponse> findAll() {
        List <Cliente> clienteList = clienteRepository.findAll();
        return clienteList.stream().map(this::toResponse).toList();
    }

    public ClienteResponse findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Cliente non trovato"));
        return toResponse(cliente);
    }

    public ClienteResponse create(@Valid ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteRequest, cliente);
        clienteRepository.save(cliente);
        return toResponse(cliente);
    }

    public ClienteResponse update(Long id, @Valid ClienteRequest clienteRequest) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Cliente non trovato"));
        BeanUtils.copyProperties(clienteRequest, cliente);
        clienteRepository.save(cliente);
        return toResponse(cliente);
    }

    public void delete(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Cliente non trovato"));
        clienteRepository.delete(cliente);
    }
}
