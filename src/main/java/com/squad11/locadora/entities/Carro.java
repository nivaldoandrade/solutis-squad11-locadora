package com.squad11.locadora.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "carro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chassi")
    private String chassi;

    @Column(name = "placa")
    private String placa;

    @Column(name = "valor_diario")
    private BigDecimal valorDiaria;

    @OneToMany(mappedBy = "carro")
    private List<Aluguel> alugueis;

    public Carro(String chassi, String placa, BigDecimal valorDiaria) {
        this.chassi = chassi;
        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }
}
