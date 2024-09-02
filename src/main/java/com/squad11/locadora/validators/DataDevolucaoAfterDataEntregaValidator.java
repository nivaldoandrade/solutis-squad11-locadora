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
            LocalDate dataEntrega = formatStringToDate(dto.dataInicio());
            LocalDate dataDevolucao = formatStringToDate(dto.dataTermino());

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();

            return !dataDevolucao.isBefore(dataEntrega);
        } catch (Exception e) {
            return false;
        }
    }
}