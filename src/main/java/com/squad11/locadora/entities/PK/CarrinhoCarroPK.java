package com.squad11.locadora.entities.PK;


import com.squad11.locadora.entities.aluguel.Carrinho;
import com.squad11.locadora.entities.carro.Carro;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode(of = {"carrinho", "carro"})
public class CarrinhoCarroPK {

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;
}
