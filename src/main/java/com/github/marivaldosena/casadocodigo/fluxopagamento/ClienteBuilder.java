package com.github.marivaldosena.casadocodigo.fluxopagamento;

import com.github.marivaldosena.casadocodigo.paises.Estado;
import com.github.marivaldosena.casadocodigo.paises.Pais;

public class ClienteBuilder {
    private String nome;
    private String sobrenome;
    private String email;
    private String documento;
    private String endereco;
    private String complemento;
    private String cidade;
    private Pais pais;
    private Estado estado;
    private String telefone;
    private String cep;

    public ClienteBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteBuilder comSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public ClienteBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public ClienteBuilder comDocumento(String documento) {
        this.documento = documento;
        return this;
    }

    public ClienteBuilder comEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public ClienteBuilder comComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public ClienteBuilder comCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public ClienteBuilder comPais(Pais pais) {
        this.pais = pais;
        return this;
    }

    public ClienteBuilder comEstado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public ClienteBuilder comTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public ClienteBuilder comCep(String cep) {
        this.cep = cep;
        return this;
    }

    public Cliente criar() {
        return new Cliente(nome, sobrenome, email, documento, endereco, complemento, cidade, pais, estado, telefone, cep);
    }
}
