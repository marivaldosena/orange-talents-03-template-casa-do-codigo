package com.github.marivaldosena.casadocodigo.fluxopagamento;

import com.github.marivaldosena.casadocodigo.paises.Estado;
import com.github.marivaldosena.casadocodigo.paises.Pais;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String complemento;

    @Column(nullable = false)
    private String cidade;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pais_id", nullable = false, foreignKey = @ForeignKey(name = "pais_id_fk"))
    private Pais pais;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_id", foreignKey = @ForeignKey(name = "estado_id_fk"))
    private Estado estado;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String cep;

    public Cliente() {
    }

    public Cliente(String nome, String sobrenome, String email, String documento, String endereco, String complemento, String cidade, Pais pais, Estado estado, String telefone, String cep) {
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

    public Long getId() {
        return id;
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

    public Pais getPais() {
        return pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }
}
