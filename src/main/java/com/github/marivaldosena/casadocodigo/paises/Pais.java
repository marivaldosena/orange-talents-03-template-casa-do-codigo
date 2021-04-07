package com.github.marivaldosena.casadocodigo.paises;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paises")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "pais")
    private List<Estado> estados;

    /**
     * @deprecated Hibernate only.
     */
    public Pais() {
    }

    /**
     *
     * @param nome Nome do país.
     */
    public Pais(String nome) {
        this.nome = nome;
        this.estados = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Estado> getEstados() {
        return estados;
    }
}
