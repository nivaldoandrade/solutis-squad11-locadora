package com.squad11.locadora.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "motorista")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Motorista extends Pessoa {

    @Column(name = "numero_cnh", nullable = false)
    private String numeroCNH;

    @JsonIgnore
    @OneToMany(mappedBy = "motorista")
    private List<Aluguel> alugueis;

    @Builder
    public Motorista(
            Long id,
            String nome,
            LocalDate dataNascimento,
            String cpf,
            String email,
            SexoEnum sexo,
            String numeroCNH,
            boolean aceitouTermos
    ) {
        super(id, nome, dataNascimento, cpf, email, sexo, aceitouTermos);
        this.numeroCNH = numeroCNH;
    }
}
