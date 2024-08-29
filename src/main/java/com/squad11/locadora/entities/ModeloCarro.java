package com.squad11.locadora.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ModeloCarro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    @ManyToOne
    private Fabricante fabricante;

    public ModeloCarro(String descricao, CategoriaEnum categoria, Fabricante fabricante) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.fabricante = fabricante;
    }
}
