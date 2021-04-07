package com.github.marivaldosena.casadocodigo.paises;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaisRepository extends CrudRepository<Pais, Long> {
    Optional<Pais> findByNome(String nomeDoPais);
}
