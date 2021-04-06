package com.github.marivaldosena.casadocodigo.livros;

import com.github.marivaldosena.casadocodigo.autores.Autor;
import com.github.marivaldosena.casadocodigo.autores.AutorRepository;
import com.github.marivaldosena.casadocodigo.categorias.Categoria;
import com.github.marivaldosena.casadocodigo.categorias.CategoriaRepository;
import com.github.marivaldosena.casadocodigo.constraints.Existe;
import com.github.marivaldosena.casadocodigo.constraints.ValorUnico;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class LivroForm {
    @NotNull
    @NotEmpty
    @ValorUnico(entidade = Livro.class, campo = "titulo")
    private String titulo;

    @NotNull
    @NotEmpty
    @Size(max = 500)
    private String resumo;

    private String sumario;

    @NotNull
    @DecimalMin(value = "20")
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer numeroDePaginas;

    @NotNull
    @NotEmpty
    @ValorUnico(entidade = Livro.class, campo = "isbn")
    private String isbn;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataDePublicacao;

    @NotNull
    @NotEmpty
    @Existe(entidade = Categoria.class, campo = "nome")
    private String categoria;

    @NotNull
    @NotEmpty
    @Email
    @Existe(entidade = Autor.class, campo = "email")
    private String autor;

    /**
     *
     * @param titulo Título do livro.
     * @param resumo Resumo obrigatório.
     * @param sumario Sumário em formato markdown.
     * @param preco Preço unitário.
     * @param numeroDePaginas Número de páginas.
     * @param isbn ISBN em formato livre.
     * @param dataDePublicacao Data de publicação do livro.
     * @param categoria Nome da categoria.
     * @param autor E-mail do autor.
     */
    public LivroForm(String titulo, String resumo, String sumario, BigDecimal preco, Integer numeroDePaginas, String isbn, LocalDateTime dataDePublicacao, String categoria, String autor) {
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

    public Integer getNumeroDePaginas() {
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

    public Livro toEntity(CategoriaRepository categoriaRepository, AutorRepository autorRepository) {
        Optional<Categoria> categoriaEncontrada = categoriaRepository.findByNome(categoria);
        Optional<Autor> autorEncontrado = autorRepository.findByEmail(autor);

        Livro livro = new LivroBuilder()
                .comTitulo(titulo)
                .comResumo(resumo)
                .comSumario(sumario)
                .comPreco(preco)
                .comIsbn(isbn)
                .comNumeroDePaginas(numeroDePaginas)
                .comCategoria(categoriaEncontrada.get())
                .comAutor(autorEncontrado.get())
                .comDataDePublicacao(dataDePublicacao)
                .criar();

        return livro;
    }
}
