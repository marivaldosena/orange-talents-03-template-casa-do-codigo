package com.github.marivaldosena.casadocodigo.categorias;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.marivaldosena.casadocodigo.constraints.ValorUnico;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoriaForm {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 120)
    @ValorUnico(entidade = Categoria.class, campo = "nome")
    private String nome;

    /**
     *
     * @param nome Nome da categoria
     */
    @JsonCreator
    public CategoriaForm(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Categoria toEntity() {
        return new Categoria(this.nome);
    }
}
