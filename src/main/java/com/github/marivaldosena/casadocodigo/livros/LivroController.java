package com.github.marivaldosena.casadocodigo.livros;

import com.github.marivaldosena.casadocodigo.autores.AutorRepository;
import com.github.marivaldosena.casadocodigo.categorias.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(LivroController.CAMINHO_DO_RECURSO)
public class LivroController {
    final static String CAMINHO_DO_RECURSO = "/api/livros";

    private LivroRepository livroRepository;
    private CategoriaRepository categoriaRepository;
    private AutorRepository autorRepository;

    @Autowired
    public LivroController(LivroRepository livroRepository, CategoriaRepository categoriaRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.categoriaRepository = categoriaRepository;
        this.autorRepository = autorRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<LivroDto> cadastrarLivro(@RequestBody @Valid LivroForm form, UriComponentsBuilder uriBuilder) {
        Livro livro = form.toEntity(categoriaRepository, autorRepository);
        livroRepository.save(livro);
        URI uri = uriBuilder.path(CAMINHO_DO_RECURSO + "/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.ok().header("Location", uri.toString()).body(new LivroDto(livro));
    }
}
