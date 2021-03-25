package com.github.marivaldosena.casadocodigo.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValorUnicoValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {
    String message() default "Valores não podem ser duplicados";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String campo() default "";
    Class<?> entidade();
}
