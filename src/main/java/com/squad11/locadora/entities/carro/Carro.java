package com.squad11.locadora.entities.carro;

import com.squad11.locadora.entities.enums.StatusCarroEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@EntityListeners(CarroEntityListener.class)
@Table(name = "carro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chassi", nullable = false)
    private String chassi;

    @Column(name = "placa", nullable = false)
    private String placa;

    @Column(name = "cor", nullable = false)
    private String cor;

    @Column(name = "valor_diario", nullable = false)
    private BigDecimal valorDiaria;

    private String foto;

//    @JsonIgnore
//    @OneToMany(mappedBy = "carro")
//    private List<Aluguel> alugueis;

    @ManyToOne
    private ModeloCarro modelo;

    @Enumerated(EnumType.STRING)
    private StatusCarroEnum status;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "carro_acessorios",
            joinColumns = @JoinColumn(name = "carro_id"),
            inverseJoinColumns = @JoinColumn(name = "acessorio_id"))
    private List<Acessorio> acessorios;


    public Carro(
            String chassi,
            String placa,
            String cor,
            BigDecimal valorDiaria,
            String foto,
            ModeloCarro modelo,
            StatusCarroEnum status,
            List<Acessorio> acessorios
    ) {

        this.chassi = chassi;
        this.placa = placa;
        this.cor = cor;
        this.valorDiaria = valorDiaria;
        this.foto = foto;
        this.modelo = modelo;
        this.status = status;
        this.acessorios = acessorios;
    }
}
