package com.github.marivaldosena.casadocodigo.autores;

import com.github.marivaldosena.casadocodigo.livros.Livro;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 400)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime instante;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

    /**
     * @deprecated Hibernate only.
     */
    public Autor() {
    }

    /**
     *
     * @param nome Nome do autor.
     * @param email E-mail principal.
     * @param descricao Breve descrição.
     */
    public Autor(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.instante = LocalDateTime.now();
        this.livros = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public List<Livro> getLivros() {
        return livros;
    }
}
