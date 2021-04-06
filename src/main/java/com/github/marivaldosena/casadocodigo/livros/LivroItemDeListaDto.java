package com.github.marivaldosena.casadocodigo.livros;

public class LivroItemDeListaDto {
    private Long id;
    private String titulo;

    public LivroItemDeListaDto(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
