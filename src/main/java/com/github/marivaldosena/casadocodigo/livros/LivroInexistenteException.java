package com.github.marivaldosena.casadocodigo.livros;

public class LivroInexistenteException extends RuntimeException {
    public LivroInexistenteException(String mensagem) {
        super(mensagem);
    }
}
