package com.github.marivaldosena.casadocodigo.fluxopagamento;

public class ClienteDto {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.sobrenome = cliente.getSobrenome();
        this.email = cliente.getEmail();
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
}
