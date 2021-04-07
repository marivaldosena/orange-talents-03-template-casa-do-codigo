package com.github.marivaldosena.casadocodigo.paises;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EstadoRepository extends CrudRepository<Estado, Long> {
    Optional<Estado> findByNomeAndPaisId(String nomeDoEstado, Long paisId);
}
