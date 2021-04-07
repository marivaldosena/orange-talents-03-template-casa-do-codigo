package com.github.marivaldosena.casadocodigo.fluxopagamento;

import com.github.marivaldosena.casadocodigo.constraints.Existe;
import com.github.marivaldosena.casadocodigo.constraints.ValorUnico;
import com.github.marivaldosena.casadocodigo.paises.Pais;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ClienteForm {
    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String sobrenome;

    @NotNull
    @NotEmpty
    @Email
    @ValorUnico(entidade = Cliente.class, campo = "email")
    private String email;

    @NotNull
    @NotEmpty
    @ValorUnico(entidade = Cliente.class, campo = "documento")
    private String documento;

    @NotNull
    @NotEmpty
    private String endereco;

    @NotNull
    @NotEmpty
    private String complemento;

    @NotNull
    @NotEmpty
    private String cidade;

    @NotNull
    @NotEmpty
    @Existe(entidade = Pais.class, campo = "nome")
    private String pais;

    private String estado;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "(^\\([\\d]{2}\\) [\\d]{4,5}-[\\d]{4}$)")
    private String telefone;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[\\d]{5}-[\\d]{3}$")
    private String cep;

    public ClienteForm(String nome, String sobrenome, String email, String documento, String endereco, String complemento, String cidade, String pais, String estado, String telefone, String cep) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.estado = estado;
        this.telefone = telefone;
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }
}
