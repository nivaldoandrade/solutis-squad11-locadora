package com.squad11.locadora.dtos;

import com.squad11.locadora.entities.Motorista;

import java.time.LocalDate;

public record MotoristaDTO(
        Long id,
        String nome,

        LocalDate dataNascimento,

        String cpf,

        String email,

        String numeroCNH

) {

    public static MotoristaDTO from(Motorista motorista) {
        return new MotoristaDTO(
                motorista.getId(),
                motorista.getNome(),
                motorista.getDataNascimento(),
                motorista.getCpf(),
                motorista.getEmail(),
                motorista.getNumeroCNH()
        );
    }
}
