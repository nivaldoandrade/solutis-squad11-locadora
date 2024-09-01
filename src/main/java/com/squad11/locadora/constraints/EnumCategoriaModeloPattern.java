package com.squad11.locadora.constraints;

import com.squad11.locadora.validators.EnumCategoriaModeloPatternValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumCategoriaModeloPatternValidator.class)
public @interface EnumCategoriaModeloPattern {
    String message() default "A Caterogia é obrigátoria";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
