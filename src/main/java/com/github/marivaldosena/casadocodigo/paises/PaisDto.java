package com.github.marivaldosena.casadocodigo.paises;

import java.util.List;
import java.util.stream.Collectors;

public class PaisDto {
    private String nome;
    private List<Estado> estados;

    public PaisDto(Pais pais) {
        this.nome = pais.getNome();
        this.estados = pais.getEstados();
    }

    public String getNome() {
        return nome;
    }

    public List<String> getEstados() {
        return estados.stream().map(Estado::getNome).collect(Collectors.toList());
    }
}
