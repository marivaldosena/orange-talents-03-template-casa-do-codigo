package com.github.marivaldosena.casadocodigo.paises;

public class EstadoDto {
    private String nome;
    private String pais;

    public EstadoDto(Estado estado) {
        this.nome = estado.getNome();
        this.pais = estado.getPais().getNome();
    }

    public String getEstado() {
        return nome;
    }

    public String getPais() {
        return pais;
    }
}
