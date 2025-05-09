package it.epicode.build_5.fatture;

import it.epicode.build_5.clienti.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @PostMapping("/clienti/{idCliente}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public FatturaResponse create(@RequestBody @Valid FatturaRequest fatturaRequest, @PathVariable Long idCliente) {
        return fatturaService.create(fatturaRequest, idCliente);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public FatturaResponse update(@PathVariable Long id, @RequestBody @Valid FatturaRequest fatturaRequest) {
        return fatturaService.update(id, fatturaRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        fatturaService.delete(id);
    }
    
    @GetMapping("/filter")
    @PreAuthorize("hasRole('ADMIN')")
    public List<FatturaResponse> filtraFatture(
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam(required = false) String numero,
            @RequestParam(required = false) Integer anno,
            @RequestParam(required = false) Integer importoMin,
            @RequestParam(required = false) Integer importoMax,
            @RequestParam(required = false) StatoFattura stato
    ) {
        return fatturaService.filtraFatture(clienteId, data, numero, anno, importoMin, importoMax, stato);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Fattura findById(@PathVariable Long id) {
        return fatturaService.findById(id);
    }
}

