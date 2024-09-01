package com.squad11.locadora.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.squad11.locadora.entities.PK.CarrinhoCarroPK;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "carrinho_carro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemCarrinho {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @EmbeddedId
    private CarrinhoCarroPK id = new CarrinhoCarroPK();

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_termino", nullable = false)
    private LocalDate dataTermino;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal = new BigDecimal(0);

    @ManyToOne(fetch = FetchType.EAGER,  optional = false)
    private Apolice apolice;

    public ItemCarrinho(
            Carrinho carrinho,
            Carro carro,
            LocalDate dataInicio,
            LocalDate dataTermino,
            Apolice apolice
    ) {
        this.id.setCarrinho(carrinho);
        this.id.setCarro(carro);
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.apolice = apolice;
        this.valorTotal = setValorTotal();
    }

    @JsonIgnore
    public Carrinho getCarrinho() {
        return id.getCarrinho();
    }

    public void setCarrinho(Carrinho carrinho) {
        this.id.setCarrinho(carrinho);
    }

    public Carro getCarro() {
        return id.getCarro();
    }

    public void setCarro(Carro carro) {
        this.id.setCarro(carro);
    }


    public BigDecimal setValorTotal() {
        BigDecimal qte = BigDecimal.valueOf(ChronoUnit.DAYS.between(dataInicio, dataTermino) + 1);

        BigDecimal valorAluguel = getCarro().getValorDiaria().multiply(qte);
        BigDecimal valorFranquia = this.apolice.getValorFranquia();

        return valorFranquia.add(valorAluguel);
    }
}
