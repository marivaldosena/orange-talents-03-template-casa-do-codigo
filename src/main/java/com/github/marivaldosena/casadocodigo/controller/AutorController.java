package com.github.marivaldosena.casadocodigo.controller;

import com.github.marivaldosena.casadocodigo.dto.AutorResponseDto;
import com.github.marivaldosena.casadocodigo.entity.Autor;
import com.github.marivaldosena.casadocodigo.form.CadastroDeAutorForm;
import com.github.marivaldosena.casadocodigo.repository.AutorRepository;
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
        Autor autor = form.converter();
        autorRepository.save(autor);
        URI uri = uriBuilder.path(CAMINHO_DO_RECURSO + "/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).body(new AutorResponseDto(autor));
    }
}
