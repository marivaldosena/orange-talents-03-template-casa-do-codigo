package com.github.marivaldosena.casadocodigo.erros;

public class CampoComErroDto {
    private String campo;
    private String erro;

    /**
     *
     * @param campo Campo inválido
     * @param erro Mensagem de erro
     */
    public CampoComErroDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
