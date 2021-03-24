package com.github.marivaldosena.casadocodigo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}
