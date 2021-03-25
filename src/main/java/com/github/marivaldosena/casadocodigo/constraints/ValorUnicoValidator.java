package com.github.marivaldosena.casadocodigo.constraints;

import com.github.marivaldosena.casadocodigo.autores.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, String> {
    private AutorRepository autorRepository;

    @Autowired
    public ValorUnicoValidator(AutorRepository repository) {
        this.autorRepository = repository;
    }

    @Override
    public void initialize(ValorUnico valorUnico) {
    }

    @Override
    public boolean isValid(String campo, ConstraintValidatorContext context) {
        return campo != null && !autorRepository.findByEmail(campo).isPresent();
    }
}
