package com.github.marivaldosena.casadocodigo.paises;

import com.github.marivaldosena.casadocodigo.constraints.Existe;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EstadoForm {
    @NotNull
    @NotEmpty
    private String estado;

    @NotNull
    @NotEmpty
    @Existe(entidade = Pais.class, campo = "nome")
    private String pais;

    public EstadoForm(String estado, String pais) {
        this.estado = estado;
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public Estado toEntity(PaisRepository paisRepository, EstadoRepository estadoRepository) {
        Pais paisEncontrado = obterPais(pais, paisRepository);

        if (estadoRepository.findByNomeAndPaisId(estado, paisEncontrado.getId()).isPresent()) {
            throw new EstadoDuplicadoException("Estado duplicado");
        }

        return new Estado(estado, paisEncontrado);
    }

    private Pais obterPais(String nomeDePais, PaisRepository paisRepository) {
        return paisRepository.findByNome(nomeDePais).get();
    }
}
