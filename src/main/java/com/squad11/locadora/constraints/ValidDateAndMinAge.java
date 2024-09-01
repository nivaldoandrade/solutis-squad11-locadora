package com.squad11.locadora.constraints;

import com.squad11.locadora.validators.DateAndMinAgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DateAndMinAgeValidator.class})
//@Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}")
@ReportAsSingleViolation
public @interface ValidDateAndMinAge {
    String message() default "A Data de nascimento inválida. EX: dd-mm-yyyy";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
