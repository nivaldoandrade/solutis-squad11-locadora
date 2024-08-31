package com.squad11.locadora.constraints;

import com.squad11.locadora.validators.DataDevolucaoAfterDataEntregaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataDevolucaoAfterDataEntregaValidator.class)
public @interface DataDevolucaoAfterDataEntregaPattern {
    String message() default "Data de Término tem que ser maior do que a data de inicio";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName() default "dataTermino";
}