package com.squad11.locadora.validators;

import com.squad11.locadora.constraints.DataDevolucaoAfterDataEntregaPattern;
import com.squad11.locadora.dtos.aluguel.request.CreateItemCarrinhoDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

import static com.squad11.locadora.utils.DateUtils.formatStringToDate;

public class DataDevolucaoAfterDataEntregaValidator
        implements ConstraintValidator<DataDevolucaoAfterDataEntregaPattern, CreateItemCarrinhoDTO> {

    private String fieldName;

    @Override
    public void initialize(DataDevolucaoAfterDataEntregaPattern constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(CreateItemCarrinhoDTO dto, ConstraintValidatorContext context) {
        try {
            LocalDate now = LocalDate.now();
            LocalDate dataInicio = formatStringToDate(dto.dataInicio());
            LocalDate dataTermino = formatStringToDate(dto.dataTermino());

            if (dataInicio.isBefore(now)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("A data de inicio deve ser igual ou maior que a data atual")
                        .addPropertyNode("dataInicio")
                        .addConstraintViolation();

                return false;
            }

            if (dataTermino.isBefore(now)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("A data de termino deve ser igual ou maior que a data atual")
                        .addPropertyNode("dataTermino")
                        .addConstraintViolation();

                return false;
            }


            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();

            return !dataTermino.isBefore(dataInicio);
        } catch (Exception e) {
            return false;
        }
    }
}