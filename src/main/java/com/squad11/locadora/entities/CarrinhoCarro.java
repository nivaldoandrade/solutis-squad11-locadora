package com.squad11.locadora.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.squad11.locadora.entities.PK.CarrinhoCarroPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class CarrinhoCarro {

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

    public CarrinhoCarro(
            Carrinho carrinho,
            Carro carro,
            LocalDate dataInicio,
            LocalDate dataTermino
    ) {
        this.id.setCarrinho(carrinho);
        this.id.setCarro(carro);
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
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
        return getCarro().getValorDiaria().multiply(qte);
    }
}
