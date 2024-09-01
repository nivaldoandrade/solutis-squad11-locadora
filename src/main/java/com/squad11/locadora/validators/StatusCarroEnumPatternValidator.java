package com.squad11.locadora.validators;

import com.squad11.locadora.constraints.StatusCarroEnumPattern;
import com.squad11.locadora.entities.StatusCarroEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StatusCarroEnumPatternValidator
        implements ConstraintValidator<StatusCarroEnumPattern, String> {
    private static List<String> acceptedValues;

    @Override
    public void initialize(StatusCarroEnumPattern constraintAnnotation) {
        acceptedValues = Arrays.stream(StatusCarroEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {

            return false;
        }

        if(!acceptedValues.contains(value.toUpperCase())) {
            String message = generateMessageStatusCarro();

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        };

        return acceptedValues.contains(value.toUpperCase());
    }

    public static String generateMessageStatusCarro() {
        String valores = String.join(" ou ", acceptedValues);

        return "Escolha o status do carro entre: " + valores;
    }
}
