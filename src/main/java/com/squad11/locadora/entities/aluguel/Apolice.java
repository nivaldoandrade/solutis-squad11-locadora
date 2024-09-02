package com.squad11.locadora.entities.aluguel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "apolice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Apolice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_franquia", nullable = false)
    private BigDecimal valorFranquia;

    @Column(name = "protecao_terceiro", nullable = false)
    private Boolean protecaoTerceiro;

    @Column(name = "protecao_causas_naturais", nullable = false)
    private Boolean protecaoCausasNaturais;

    @Column(name = "protecao_roubo", nullable = false)
    private Boolean protecaoRoubo;

    @OneToOne
    @JsonIgnore
    private Aluguel aluguel;

    public Apolice(
            BigDecimal valorFranquia,
            Boolean protecaoTerceiro,
            Boolean protecaoCausasNaturais,
            Boolean protecaoRoubo
    ) {
        this.valorFranquia = valorFranquia;
        this.protecaoTerceiro = protecaoTerceiro;
        this.protecaoCausasNaturais = protecaoCausasNaturais;
        this.protecaoRoubo = protecaoRoubo;
    }
}
