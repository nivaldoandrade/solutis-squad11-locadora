package com.squad11.locadora.entities;

import jakarta.persistence.*;
import lombok.*;

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

//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JsonIgnore
//    private List<Carro> carros;

    public Acessorio(String descricao) {
        this.descricao = descricao;
    }
}
