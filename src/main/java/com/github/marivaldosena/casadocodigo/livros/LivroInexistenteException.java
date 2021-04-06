package com.github.marivaldosena.casadocodigo.livros;

public class LivroInexistenteException extends RuntimeException {
    private Long id;

    public LivroInexistenteException(Long id, String mensagem) {
        super("Id: " + id + " - " + mensagem);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
