package com.squad11.locadora.validators;

import com.squad11.locadora.constraints.EnumCategoriaModeloPattern;
import com.squad11.locadora.entities.CategoriaEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumCategoriaModeloPatternValidator
        implements ConstraintValidator<EnumCategoriaModeloPattern, String> {
    private static List<String> acceptedValues;

    @Override
    public void initialize(EnumCategoriaModeloPattern constraintAnnotation) {
        acceptedValues = Arrays.stream(CategoriaEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {

            return false;
        }

        if(!acceptedValues.contains(value.toUpperCase())) {
            String message = generateMessageEnumCategoriaModelo();

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        };

        return acceptedValues.contains(value.toUpperCase());
    }

    public static String generateMessageEnumCategoriaModelo() {
        String valores = String.join(" ou ", acceptedValues);

        return "Escolha a categoria entre: " + valores;
    }
}
