package com.github.marivaldosena.casadocodigo.paises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class PaisController {
    private PaisRepository paisRepository;
    private EstadoRepository estadoRepository;

    @Autowired
    public PaisController(PaisRepository paisRepository, EstadoRepository estadoRepository) {
        this.paisRepository = paisRepository;
        this.estadoRepository = estadoRepository;
    }

    @PostMapping("/api/paises")
    public ResponseEntity<PaisDto> cadastrarPais(@RequestBody @Valid PaisForm form, UriComponentsBuilder uriBuilder) {
        Pais pais = form.toEntity();
        paisRepository.save(pais);
        URI uri = uriBuilder.path("/api/paises/{id}").buildAndExpand(pais.getId()).toUri();
        return ResponseEntity.ok().header("Location", uri.toString()).body(new PaisDto(pais));
    }
}
