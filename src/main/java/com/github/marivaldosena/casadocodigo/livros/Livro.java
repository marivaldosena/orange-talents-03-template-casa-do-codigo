package com.github.marivaldosena.casadocodigo.livros;

import com.github.marivaldosena.casadocodigo.autores.Autor;
import com.github.marivaldosena.casadocodigo.categorias.Categoria;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String resumo;

    @Lob
    @Column(nullable = true)
    private String sumario;

    @Min(20)
    private BigDecimal preco;

    @DecimalMin(value = "100")
    @Column(name = "numero_paginas", nullable = false)
    private Integer numeroDePaginas;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(name = "data_publicacao")
    private LocalDateTime dataDePublicacao;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false, foreignKey = @ForeignKey(name = "categoria_id_fk"))
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "autor_id", nullable = false, foreignKey = @ForeignKey(name = "autor_id_fk"))
    private Autor autor;

    /**
     * @deprecated Hibernate only.
     */
    public Livro() {
    }

    /**
     *
     * @param titulo Título do livro.
     * @param resumo Resumo obrigatório.
     * @param sumario Sumário em formato markdown.
     * @param preco Preço unitário.
     * @param numeroDePaginas Número de páginas.
     * @param isbn ISBN em formato livre.
     * @param dataDePublicacao Data de publicação do livro.
     * @param categoria Categoria do livro.
     * @param autor Autor principal.
     */
    public Livro(String titulo, String resumo, String sumario, BigDecimal preco, int numeroDePaginas, String isbn, LocalDateTime dataDePublicacao, Categoria categoria, Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataDePublicacao = dataDePublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Long getId() {
        return id;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
