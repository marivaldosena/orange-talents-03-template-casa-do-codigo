package com.github.marivaldosena.casadocodigo.livros;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LivroRepository extends CrudRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);
}
