package com.github.marivaldosena.casadocodigo.dto;

import com.github.marivaldosena.casadocodigo.entity.Autor;

public class AutorResponseDto {
    private String nome;
    private String email;

    public AutorResponseDto(Autor autor) {
        this.nome = autor.getNome();
        this.email = autor.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
