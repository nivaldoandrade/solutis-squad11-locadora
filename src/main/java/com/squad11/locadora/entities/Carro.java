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

    @ManyToOne
    private ModeloCarro modelo;

    private Boolean isDisponivel;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "carro_acessorios",
            joinColumns = @JoinColumn(name = "carro_id"),
            inverseJoinColumns = @JoinColumn(name = "acessorio_id"))
    private List<Acessorio> acessorios;

    public Carro(String chassi, String placa, BigDecimal valorDiaria, ModeloCarro modelo, List<Acessorio> acessorios) {

        this.chassi = chassi;
        this.placa = placa;
        this.valorDiaria = valorDiaria;
        this.modelo = modelo;
        this.acessorios = acessorios;
    }
}
