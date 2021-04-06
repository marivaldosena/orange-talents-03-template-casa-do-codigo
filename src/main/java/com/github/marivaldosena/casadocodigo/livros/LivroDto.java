package com.github.marivaldosena.casadocodigo.livros;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LivroDto {
    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private int numeroDePaginas;
    private String isbn;
    private LocalDateTime dataDePublicacao;
    private String categoria;
    private String autor;

    public LivroDto(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.isbn = livro.getIsbn();
        this.dataDePublicacao = livro.getDataDePublicacao();
        this.categoria = livro.getCategoria().getNome();
        this.autor = livro.getAutor().getNome();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDateTime getDataDePublicacao() {
        return dataDePublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getAutor() {
        return autor;
    }
}
