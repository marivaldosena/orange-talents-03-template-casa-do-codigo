package com.github.marivaldosena.casadocodigo.categorias;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.marivaldosena.casadocodigo.constraints.ValorUnico;
import com.sun.istack.NotNull;

import javax.validation.constraints.Size;

public class CadastroDeCategoriaForm {
    @NotNull
    @Size(min = 2, max = 120)
    @ValorUnico(entidade = Categoria.class, campo = "nome")
    private String nome;

    /**
     *
     * @param nome Nome da categoria
     */
    @JsonCreator
    public CadastroDeCategoriaForm(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Categoria toEntity() {
        return new Categoria(this.nome);
    }
}
