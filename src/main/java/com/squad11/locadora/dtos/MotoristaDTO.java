package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Motorista;
import com.squad11.locadora.entities.SexoEnum;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MotoristaDTO(
        Long id,

        String nome,

        LocalDate dataNascimento,

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
                .dataNascimento(motorista.getDataNascimento())
                .sexo(motorista.getSexo())
                .cpf(motorista.getCpf())
                .numeroCNH(motorista.getNumeroCNH())
                .email(motorista.getEmail())
                .aceitouTermos(motorista.isAceitouTermos())
                .ativo(motorista.isAtivo())
                .build();
    }
}
