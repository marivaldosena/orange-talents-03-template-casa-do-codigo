package com.github.marivaldosena.casadocodigo.categorias;

public class CategoriaDto {
    private String nome;

    /**
     *
     * @param categoria Instância de entidade categoria
     */
    public CategoriaDto(Categoria categoria) {
        this.nome = categoria.getNome();
    }

    public String getNome() {
        return nome;
    }
}
