package com.github.marivaldosena.casadocodigo.categorias;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String nome;

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
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
