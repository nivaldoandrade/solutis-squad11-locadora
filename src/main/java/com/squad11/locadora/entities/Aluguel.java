package com.squad11.locadora.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;

@Entity
@Table(name = "aluguel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pedido")
    private Calendar dataPedido;

    @Column(name = "data_entrega")
    private LocalDate dataEntrega;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @ManyToOne
    private Motorista motorista;

    @ManyToOne
    private Carro carro;
}
