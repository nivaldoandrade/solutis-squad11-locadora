package com.squad11.locadora.entities;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrinho")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER,  optional = false)
    @MapsId
    private Motorista motorista;

    @OneToMany(mappedBy = "id.carrinho", cascade = CascadeType.REMOVE)
    private List<ItemCarrinho> itemCarrinhos = new ArrayList<>();

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal = new BigDecimal(0);

    public Carrinho(Motorista motorista) {
        this.motorista = motorista;
    }

    public void addItemCarrinho(ItemCarrinho itemCarrinho) {
        itemCarrinhos.add(itemCarrinho);
        atualizarValorTotal();
    }

    public void removeItemCarrinho(ItemCarrinho itemCarrinho) {
        itemCarrinhos.remove(itemCarrinho);
        atualizarValorTotal();
    }

    private void atualizarValorTotal() {
        this.valorTotal = itemCarrinhos
                .stream()
                .map(ItemCarrinho::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
