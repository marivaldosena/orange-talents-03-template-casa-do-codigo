package com.github.marivaldosena.casadocodigo.autores;

import com.github.marivaldosena.casadocodigo.constraints.ValorUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CadastroDeAutorForm {
    @NotNull
    @Size(min = 2, max = 120)
    private String nome;

    @NotNull
    @NotEmpty
    @Email
    @ValorUnico(entidade = Autor.class, campo = "email")
    private String email;

    @NotNull
    @Size(min = 3, max = 400)
    private String descricao;

    /**
     *
     * @param nome Nome do autor.
     * @param email E-mail do autor.
     * @param descricao Breve descrição.
     */
    public CadastroDeAutorForm(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public Autor toEntity() {
        return new Autor(nome, email, descricao);
    }
}
