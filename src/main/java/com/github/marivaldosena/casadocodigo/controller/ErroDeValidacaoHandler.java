package com.github.marivaldosena.casadocodigo.controller;

import com.github.marivaldosena.casadocodigo.dto.CadastroDeAutorErrorDto;
import com.github.marivaldosena.casadocodigo.entity.ErrosDto;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
    private MessageSource messageSource;

    public ErroDeValidacaoHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrosDto handle(MethodArgumentNotValidException exception) {
        List<CadastroDeAutorErrorDto> listaDeErros = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(erro -> {
            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            CadastroDeAutorErrorDto dto = new CadastroDeAutorErrorDto(erro.getField(), mensagem);
            listaDeErros.add(dto);
        });

        return new ErrosDto(listaDeErros);
    }
}
