package com.github.marivaldosena.casadocodigo.livros;

import com.github.marivaldosena.casadocodigo.autores.Autor;
import com.github.marivaldosena.casadocodigo.categorias.Categoria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LivroBuilder {
    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Integer numeroDePaginas;
    private String isbn;
    private LocalDateTime dataDePublicacao;
    private Autor autor;
    private Categoria categoria;

    public LivroBuilder comTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public LivroBuilder comResumo(String resumo) {
        this.resumo = resumo;
        return this;
    }

    public LivroBuilder comSumario(String sumario) {
        this.sumario = sumario;
        return this;
    }

    public LivroBuilder comPreco(BigDecimal preco) {
        this.preco = preco;
        return this;
    }

    public LivroBuilder comNumeroDePaginas(Integer numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
        return this;
    }

    public LivroBuilder comIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public LivroBuilder comDataDePublicacao(LocalDateTime dataDePublicacao) {
        this.dataDePublicacao = dataDePublicacao;
        return this;
    }

    public LivroBuilder comCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public LivroBuilder comAutor(Autor autor) {
        this.autor = autor;
        return this;
    }

    public Livro criar() {
        return new Livro(titulo, resumo, sumario, preco, numeroDePaginas, isbn, dataDePublicacao, categoria, autor);
    }
}
