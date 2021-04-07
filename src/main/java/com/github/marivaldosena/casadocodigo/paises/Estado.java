package com.github.marivaldosena.casadocodigo.paises;

import javax.persistence.*;

@Entity
@Table(name = "estados",
        uniqueConstraints = @UniqueConstraint(name = "estado_unico_por_pais_uc",
                columnNames = {"pais_id", "nome"}))
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pais_id", nullable = false, foreignKey = @ForeignKey(name = "pais_id_fk"))
    private Pais pais;

    /**
     * @deprecated Hibernate only.
     */
    public Estado() {
    }

    /**
     *
     * @param nome Nome do estado.
     * @param pais País ao qual o estado pertence.
     */
    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Pais getPais() {
        return pais;
    }
}
