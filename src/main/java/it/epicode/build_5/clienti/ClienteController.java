package it.epicode.build_5.clienti;

import it.epicode.build_5.comuni.Comune;
import it.epicode.build_5.indirizzi.Indirizzo;
import it.epicode.build_5.indirizzi.IndirizzoRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PatchMapping(path = "/{id}/logoaziendale", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('USER')")
    public void uploadLogoAziendale(@RequestParam Long id, @RequestParam MultipartFile file) {
        clienteService.uploadLogoAziendale(id, file);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<ClienteResponse>  findAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Cliente findById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public Cliente create(@RequestBody @Valid ClienteFullRequest clienteFullRequest, String comuneSedeLegale, String comuneSedeOperativa, TipoCliente tipoCliente) {
        return clienteService.create(clienteFullRequest, comuneSedeLegale, comuneSedeOperativa, tipoCliente);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Cliente update(@PathVariable Long id, @RequestBody @Valid ClienteRequest clienteRequest) {
        return clienteService.update(id, clienteRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    public void delete(@PathVariable Long id) {
        clienteService.delete(id);
    }
    
    @GetMapping("/sorted")
    @PreAuthorize("isAuthenticated()")
    public Page<ClienteResponse> getSortedClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ragioneSociale") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return clienteService.findAllSorted(page, size, sortBy, direction);
    }

    @GetMapping("/filter")
    @PreAuthorize("isAuthenticated()")
    public Page<ClienteResponse> filterClienti(
            @RequestParam(required = false) Integer fatturatoMinimo,
            @RequestParam(required = false) Integer annoInserimento,
            @RequestParam(required = false) Integer annoUltimoContatto,
            @RequestParam(required = false) String nomeParziale,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ragioneSociale") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return clienteService.filtraClienti(
                fatturatoMinimo, annoInserimento, annoUltimoContatto, nomeParziale,
                page, size, sortBy, direction
        );
    }
}
