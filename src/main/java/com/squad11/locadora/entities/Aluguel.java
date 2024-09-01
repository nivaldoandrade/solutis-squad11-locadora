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

    @Column(name = "data_pedido", nullable = false)
    private LocalDate dataPedido;

    @Column(name = "data_entrega", nullable = false)
    private LocalDate dataEntrega;

    @Column(name = "data_devolucao", nullable = false)
    private LocalDate dataDevolucao;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne
    private Motorista motorista;

    @ManyToOne
    private Carro carro;

    @OneToOne(fetch = FetchType.EAGER)
    private Apolice apolice;

    @Enumerated(EnumType.STRING)
    private StatusAluguelEnum status = StatusAluguelEnum.PENDENTE;

    @Column(name = "aceitou_termos", nullable = false)
    private Boolean aceitouTermos;

    public Aluguel(
            LocalDate dataPedido,
            LocalDate dataEntrega,
            LocalDate dataDevolucao,
            BigDecimal valorTotal,
            Motorista motorista,
            Carro carro,
            Apolice apolice,
            StatusAluguelEnum status
    ) {
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.dataDevolucao = dataDevolucao;
        this.valorTotal = valorTotal;
        this.motorista = motorista;
        this.carro = carro;
        this.apolice = apolice;
        this.status = status;
    }
}
