package com.github.marivaldosena.casadocodigo.categorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(CategoriaController.CAMINHO_DO_RECURSO)
public class CategoriaController {
    final static String CAMINHO_DO_RECURSO = "/api/categorias";
    private CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> cadastrarCategoria(@RequestBody @Valid CadastroDeCategoriaForm form, UriComponentsBuilder uriBuilder) {
        Categoria categoria = form.toEntity();
        categoriaRepository.save(categoria);
        URI uri = uriBuilder.path(CAMINHO_DO_RECURSO + "/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.ok().header("Location", uri.toString()).body(new CategoriaDto(categoria));
    }
}
