package com.squad11.locadora.dtos.pessoa;

import com.squad11.locadora.entities.pessoa.Motorista;
import com.squad11.locadora.entities.enums.SexoEnum;
import com.squad11.locadora.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

import static com.squad11.locadora.utils.DateUtils.formatDateToString;

@Builder
public record MotoristaDTO(
        Long id,

        String nome,

        @Schema(example = "dd-mm-yyyy")
        String dataNascimento,

        SexoEnum sexo,

        String cpf,

        String numeroCNH,

        String email,

        Boolean aceitouTermos,

        Boolean ativo

) {

    public static MotoristaDTO from(Motorista motorista) {
        return MotoristaDTO.builder()
                .id(motorista.getId())
                .nome(motorista.getNome())
                .dataNascimento(formatDateToString(motorista.getDataNascimento()))
                .sexo(motorista.getSexo())
                .cpf(motorista.getCpf())
                .numeroCNH(motorista.getNumeroCNH())
                .email(motorista.getEmail())
                .aceitouTermos(motorista.isAceitouTermos())
                .ativo(motorista.isAtivo())
                .build();
    }
}
