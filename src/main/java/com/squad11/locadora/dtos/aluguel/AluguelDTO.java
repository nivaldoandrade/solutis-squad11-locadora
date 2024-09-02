package com.squad11.locadora.dtos.aluguel;

import com.squad11.locadora.dtos.carro.CarroDTO;
import com.squad11.locadora.dtos.pessoa.MotoristaDTO;
import com.squad11.locadora.entities.aluguel.Aluguel;
import com.squad11.locadora.entities.enums.StatusAluguelEnum;
import com.squad11.locadora.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.squad11.locadora.utils.DateUtils.formatDateToString;

@Builder
public record AluguelDTO(
        Long id,

        @Schema(example = "dd-mm-yyyy")
        String dataPedido,

        @Schema(example = "dd-mm-yyyy")
        String dataEntrega,

        @Schema(example = "dd-mm-yyyy")
        String dataDevolucao,

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
                .dataPedido(formatDateToString(aluguel.getDataPedido()))
                .dataEntrega(formatDateToString(aluguel.getDataEntrega()))
                .dataDevolucao(formatDateToString(aluguel.getDataDevolucao()))
                .valorTotal(aluguel.getValorTotal())
                .status(aluguel.getStatus())
                .termos(aluguel.getAceitouTermos())
                .motorista(MotoristaDTO.from(aluguel.getMotorista()))
                .carro(CarroDTO.from(aluguel.getCarro()))
                .apolice(ApoliceDTO.from(aluguel.getApolice()))
                .build();
    }
}
