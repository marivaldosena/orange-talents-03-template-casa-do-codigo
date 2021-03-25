package com.github.marivaldosena.casadocodigo.autores;

import org.springframework.data.repository.CrudRepository;

public interface AutorRepository extends CrudRepository<Autor, Long> {
    Autor findByEmail(String email);
}
