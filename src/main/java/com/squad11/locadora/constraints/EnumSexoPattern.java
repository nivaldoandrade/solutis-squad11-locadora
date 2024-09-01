package com.squad11.locadora.constraints;

import com.squad11.locadora.validators.EnumSexoPatternValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumSexoPatternValidator.class)
public @interface EnumSexoPattern {
    String message() default "O Sexo é obrigátorio";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
