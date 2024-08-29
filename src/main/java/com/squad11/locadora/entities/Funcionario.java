package com.squad11.locadora.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario extends Pessoa {

    @Column(name = "matricula")
    private String matricula;

    public Funcionario(
            Long id,
            String nome,
            LocalDate dataNascimento,
            String cpf,
            String email,
            SexoEnum sexo,
            boolean aceitouTermos,
            String matricula
    ) {
        super(id, nome, dataNascimento, cpf, email, sexo, aceitouTermos);
        this.matricula = matricula;
    }
}
