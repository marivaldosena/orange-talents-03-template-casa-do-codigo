package com.github.marivaldosena.casadocodigo.paises;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.marivaldosena.casadocodigo.constraints.ValorUnico;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PaisForm {
    @NotNull
    @NotEmpty
    @ValorUnico(entidade = Pais.class, campo = "nome")
    private String nome;

    @JsonCreator
    public PaisForm(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Pais toEntity() {
        return new Pais(nome);
    }
}
