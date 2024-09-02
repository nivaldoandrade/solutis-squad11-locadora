package com.squad11.locadora.dtos.aluguel;

import com.squad11.locadora.dtos.carro.CarroDTO;
import com.squad11.locadora.dtos.pessoa.MotoristaDTO;
import com.squad11.locadora.entities.aluguel.Aluguel;
import com.squad11.locadora.entities.enums.StatusAluguelEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record AluguelDTO(
        Long id,

        LocalDate dataPedido,

        LocalDate dataEntrega,

        LocalDate dataDevolucao,

        BigDecimal valorTotal,

        StatusAluguelEnum status,

        Boolean termos,

        MotoristaDTO motorista,

        CarroDTO carro,

        ApoliceDTO apolice
) {

    public static AluguelDTO from(Aluguel aluguel) {
        return AluguelDTO.builder()
                .id(aluguel.getId())
                .dataPedido(aluguel.getDataPedido())
                .dataEntrega(aluguel.getDataEntrega())
                .dataDevolucao(aluguel.getDataDevolucao())
                .valorTotal(aluguel.getValorTotal())
                .status(aluguel.getStatus())
                .termos(aluguel.getAceitouTermos())
                .motorista(MotoristaDTO.from(aluguel.getMotorista()))
                .carro(CarroDTO.from(aluguel.getCarro()))
                .apolice(ApoliceDTO.from(aluguel.getApolice()))
                .build();
    }
}
