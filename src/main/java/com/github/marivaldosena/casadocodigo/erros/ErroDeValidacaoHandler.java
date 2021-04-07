package com.github.marivaldosena.casadocodigo.erros;

import com.github.marivaldosena.casadocodigo.livros.LivroInexistenteException;
import com.github.marivaldosena.casadocodigo.paises.EstadoDuplicadoException;
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
        List<CampoComErroDto> listaDeErros = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(erro -> {
            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            CampoComErroDto dto = new CampoComErroDto(erro.getField(), mensagem);
            listaDeErros.add(dto);
        });

        return new ErrosDto(listaDeErros);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(LivroInexistenteException.class)
    public ErrosDto handle(LivroInexistenteException exception) {
        List<CampoComErroDto> listaDeErros = new ArrayList<>();
        String mensagem = messageSource.getMessage("LivroInexistenteException", new Object[]{exception.getLocalizedMessage()}, LocaleContextHolder.getLocale());
        CampoComErroDto dto = new CampoComErroDto("id", mensagem);
        listaDeErros.add(dto);
        return new ErrosDto(listaDeErros);
    }

    /**
     * @deprecated Acredito que haja uma forma mais simples de lidar com exceções personalizadas.
     * Talvez o caminho seja usar o padrão Strategy para lidar com diversas possíveis exceções ou
     * utilizar Generics. Neste momento, manterei desse jeito e pesquisarei outras alternativas.
     * @param exception Exceção de estado duplicado.
     * @return ErrosDto para informar ao cliente o motivo do erro.
     */
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EstadoDuplicadoException.class)
    public ErrosDto handle(EstadoDuplicadoException exception) {
        List<CampoComErroDto> listaDeErros = new ArrayList<>();
        String mensagem = messageSource.getMessage("EstadoDuplicadoException", new Object[]{exception.getLocalizedMessage()}, LocaleContextHolder.getLocale());
        CampoComErroDto dto = new CampoComErroDto("estado", mensagem);
        listaDeErros.add(dto);
        return new ErrosDto(listaDeErros);
    }
}
