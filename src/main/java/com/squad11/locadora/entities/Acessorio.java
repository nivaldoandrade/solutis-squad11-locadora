package com.squad11.locadora.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "acessorio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Acessorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "carro_acessorios",
            joinColumns = @JoinColumn(name = "acessorio_id"),
            inverseJoinColumns = @JoinColumn(name = "carro_id"))
    private List<Carro> carros;
}
