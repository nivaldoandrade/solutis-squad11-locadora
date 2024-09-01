package com.squad11.locadora.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @Column(nullable = false)
    private boolean ativo = false;

    @Column(name = "aceitou_termos", nullable = false)
    private boolean aceitouTermos;

    public Pessoa(
            Long id,
            String nome,
            LocalDate dataNascimento,
            String cpf,
            String email,
            SexoEnum sexo,
            Boolean aceitouTermos
    ) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.sexo = sexo;
        this.aceitouTermos = aceitouTermos;
    }

    public void ativar() {
        this.ativo = true;
    }
}
