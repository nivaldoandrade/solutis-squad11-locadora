package com.squad11.locadora.validators;

import com.squad11.locadora.constraints.ValidDateAndMinAge;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

import static com.squad11.locadora.utils.DateUtils.DTF;


public class DateAndMinAgeValidator implements ConstraintValidator<ValidDateAndMinAge, String> {
    @Override
    public void initialize(ValidDateAndMinAge constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()) {
            return true;
        }

        try {
            LocalDate date = LocalDate.parse(value, DTF);
            LocalDate now = LocalDate.now();

            Period age = Period.between(date, now);
            System.out.println(age.getYears());

            boolean isMinAge = age.getYears() >= 18;

            if(!isMinAge) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("O Motorista deve ter pelo menos 18 anos")
                        .addConstraintViolation();
            }

            return isMinAge;
        } catch (Exception e) {
            return false;
        }


    }
}
