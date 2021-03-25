package com.github.marivaldosena.casadocodigo.erros;

import java.util.Collections;
import java.util.List;

public class ErrosDto {
    private List<CadastroDeAutorErrorDto> listaDeErros;

    public ErrosDto(List<CadastroDeAutorErrorDto> listaDeErros) {
        this.listaDeErros = listaDeErros;
    }

    public List getErros() {
        return Collections.unmodifiableList(listaDeErros);
    }
}
