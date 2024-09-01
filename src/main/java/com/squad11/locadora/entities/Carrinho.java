package com.squad11.locadora.entities;


import jakarta.persistence.*;
import lombok.*;

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

    public Carrinho(Motorista motorista) {
        this.motorista = motorista;
    }

}
