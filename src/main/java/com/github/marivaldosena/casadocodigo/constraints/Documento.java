package com.github.marivaldosena.casadocodigo.constraints;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@ConstraintComposition(value = CompositionType.OR)
@Constraint(validatedBy = {})
@CPF
@CNPJ
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Documento {
    String message() default "Documento inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

