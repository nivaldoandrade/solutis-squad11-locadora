package com.squad11.locadora.validators;

import com.squad11.locadora.constraints.EnumSexoPattern;
import com.squad11.locadora.entities.enums.SexoEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumSexoPatternValidator implements ConstraintValidator<EnumSexoPattern, String> {
    private static List<String> acceptedValues;

    @Override
    public void initialize(EnumSexoPattern constraintAnnotation) {
        acceptedValues = Arrays.stream(SexoEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {

            return false;
        }

        if(!acceptedValues.contains(value.toUpperCase())) {
            String message = generateMessageSexo();

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        };

        return acceptedValues.contains(value.toUpperCase());
    }

    public static String generateMessageSexo() {
        String valores = String.join(" ou ", acceptedValues);

        return "Escolha o sexo entre: " + valores;
    }
}
