package com.github.marivaldosena.casadocodigo.entity;

import com.github.marivaldosena.casadocodigo.dto.CadastroDeAutorErrorDto;

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
