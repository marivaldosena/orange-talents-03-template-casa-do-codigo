package com.github.marivaldosena.casadocodigo.autores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(AutorController.CAMINHO_DO_RECURSO)
public class AutorController {
    final static String CAMINHO_DO_RECURSO = "/api/autores";
    private AutorRepository autorRepository;

    @Autowired
    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AutorResponseDto> cadastrarAutor(@RequestBody @Valid CadastroDeAutorForm form, UriComponentsBuilder uriBuilder) {
        Autor autor = form.toEntity();
        autorRepository.save(autor);
        URI uri = uriBuilder.path(CAMINHO_DO_RECURSO + "/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.ok().header("Location", uri.toString()).body(new AutorResponseDto(autor));
    }
}
