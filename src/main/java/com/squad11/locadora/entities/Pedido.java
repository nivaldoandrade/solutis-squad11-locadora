package com.squad11.locadora.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of =  "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Aluguel> alugueis = new ArrayList<>();

    @ManyToOne
    private Motorista motorista;

    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status = StatusPedidoEnum.PENDENTE;

    public Pedido(List<Aluguel> alugueis, Motorista motorista) {
        this.alugueis = alugueis;
        this.motorista = motorista;
    }
}
