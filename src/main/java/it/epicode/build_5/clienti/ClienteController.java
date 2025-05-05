package it.epicode.build_5.clienti;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PatchMapping(path = "/{id}/logoaziendale", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadLogoAziendale(@RequestParam Long id, @RequestParam MultipartFile file) {
        clienteService.uploadLogoAziendale(id, file);
    }

    @GetMapping
    private List<ClienteResponse>  findAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    private ClienteResponse findById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    private ClienteResponse create(@RequestBody @Valid ClienteRequest clienteRequest) {
        return clienteService.create(clienteRequest);
    }

    @PutMapping("/{id}")
    private ClienteResponse update(@PathVariable Long id, @RequestBody @Valid ClienteRequest clienteRequest) {
        return clienteService.update(id, clienteRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id) {
        clienteService.delete(id);
    }
}
