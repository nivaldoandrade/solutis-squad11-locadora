package com.squad11.locadora.validators;

import com.squad11.locadora.constraints.DateFormatPattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import static com.squad11.locadora.utils.DateUtils.formatStringToDate;

public class DateFormatValidator implements ConstraintValidator<DateFormatPattern, String> {
    @Override
    public void initialize(DateFormatPattern constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            formatStringToDate(value);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
