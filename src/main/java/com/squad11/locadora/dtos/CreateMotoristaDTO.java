package com.squad11.locadora.dtos;

import com.squad11.locadora.constraints.ValidDateAndMinAge;
import com.squad11.locadora.entities.Motorista;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import static com.squad11.locadora.utils.DateUtils.formatStringToDate;

@Builder
public record CreateMotoristaDTO(
        @NotBlank(message = "O nome é obrigátorio")
        String nome,

        @NotBlank(message = "A data de nascimento é obrigátoria")
        @ValidDateAndMinAge()
        String dataNascimento,

        @NotBlank(message = "O CPF é obrigátorio")
        @CPF(message = "O CPF é inválido")
        String cpf,

        @NotBlank(message = "O email é obrigátorio")
        @Email(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
                message = "O Email é inválido"
        )
        String email,

        @NotBlank(message = "A CNH é obrigátorio")
        @Pattern(regexp = "^\\d{11}$", message = "A CNH é inválido")
        String numeroCNH

) {

        public static Motorista to(CreateMotoristaDTO createMotoristaDTO) {
                return Motorista.builder()
                        .nome(createMotoristaDTO.nome)
                        .dataNascimento(formatStringToDate(createMotoristaDTO.dataNascimento))
                        .cpf(createMotoristaDTO.cpf)
                        .email(createMotoristaDTO.email)
                        .numeroCNH(createMotoristaDTO.numeroCNH())
                        .build();
        }
}
