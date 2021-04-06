package com.github.marivaldosena.casadocodigo.categorias;

import com.github.marivaldosena.casadocodigo.livros.Livro;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Livro> livros;

    /**
     * @deprecated Hibernate only.
     */
    public Categoria() {
    }

    /**
     *
     * @param nome Nome da categoria
     */
    public Categoria(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Livro> getLivros() {
        return livros;
    }
}
