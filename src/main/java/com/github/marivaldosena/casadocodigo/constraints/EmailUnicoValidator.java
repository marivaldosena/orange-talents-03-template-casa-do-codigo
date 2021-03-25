package com.github.marivaldosena.casadocodigo.constraints;

import com.github.marivaldosena.casadocodigo.autores.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUnicoValidator implements ConstraintValidator<EmailUnicoConstraint, String> {
    private AutorRepository autorRepository;

    @Autowired
    public EmailUnicoValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public void initialize(EmailUnicoConstraint email) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && autorRepository.findByEmail(email) == null;
    }
}
