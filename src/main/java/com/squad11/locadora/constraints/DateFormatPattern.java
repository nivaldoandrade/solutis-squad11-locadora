package com.squad11.locadora.constraints;

import com.squad11.locadora.validators.DataDevolucaoAfterDataEntregaValidator;
import com.squad11.locadora.validators.DateFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
public @interface DateFormatPattern {
    String message() default "Data inválida. EX: dd-mm-yyyy";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}