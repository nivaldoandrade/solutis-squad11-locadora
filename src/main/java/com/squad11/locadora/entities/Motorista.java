package com.squad11.locadora.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "motorista")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Motorista extends Pessoa {

    @Column()
    private String numeroCNH;

    @Builder
    public Motorista(Long id, String nome, LocalDate dataNascimento, String cpf, String email, String numeroCNH) {
        super(id, nome, dataNascimento, cpf, email);
        this.numeroCNH = numeroCNH;
    }
}
