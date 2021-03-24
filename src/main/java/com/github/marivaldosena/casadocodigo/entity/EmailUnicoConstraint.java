package com.github.marivaldosena.casadocodigo.entity;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailUnicoValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailUnicoConstraint {
    String message() default "E-mail duplicado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
