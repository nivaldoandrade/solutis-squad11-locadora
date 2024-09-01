package com.squad11.locadora.constraints;

import com.squad11.locadora.validators.EnumCategoriaModeloPatternValidator;
import com.squad11.locadora.validators.StatusCarroEnumPatternValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StatusCarroEnumPatternValidator.class)
public @interface StatusCarroEnumPattern {
    String message() default "O Status do carro é obrigátorio";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
