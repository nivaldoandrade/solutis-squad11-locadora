package com.squad11.locadora.constraints;

import com.squad11.locadora.validators.FileTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileTypeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileType {

    String message() default "O tipo do arquivo não é aceito";
    String[] allowedExtensions();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
