package com.squad11.locadora.dtos.pessoa.request;

import com.squad11.locadora.constraints.EnumSexoPattern;
import com.squad11.locadora.constraints.ValidDateAndMinAge;
import com.squad11.locadora.entities.pessoa.Motorista;
import com.squad11.locadora.entities.enums.SexoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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

        @Schema(example = "MASCULINO ou FEMININO")
        @EnumSexoPattern()
        String sexo,

        @NotNull(message = "O aceitouTermos é obrigátorio")
        @AssertTrue(message = "O aceitouTermos é obrigátorio ser TRUE")
        Boolean aceitouTermos,

        @NotBlank(message = "A CNH é obrigátorio")
        @Pattern(regexp = "^\\d{11}$", message = "A CNH é inválido")
        String numeroCNH

) {

        public static Motorista to(CreateMotoristaDTO createMotoristaDTO, SexoEnum sexo) {

                return Motorista.builder()
                        .nome(createMotoristaDTO.nome())
                        .dataNascimento(formatStringToDate(createMotoristaDTO.dataNascimento()))
                        .cpf(createMotoristaDTO.cpf())
                        .email(createMotoristaDTO.email())
                        .sexo(sexo)
                        .aceitouTermos(createMotoristaDTO.aceitouTermos())
                        .numeroCNH(createMotoristaDTO.numeroCNH())
                        .build();
        }
}
