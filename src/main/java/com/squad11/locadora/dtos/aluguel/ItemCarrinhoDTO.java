package com.squad11.locadora.dtos.aluguel;

import com.squad11.locadora.dtos.carro.CarroDTO;
import com.squad11.locadora.entities.aluguel.ItemCarrinho;
import com.squad11.locadora.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.squad11.locadora.utils.DateUtils.formatDateToString;

@Builder
public record ItemCarrinhoDTO(

        @Schema(example = "dd-mm-yyyy")
        String dataInicio,

        @Schema(example = "dd-mm-yyyy")
        String dataTermino,

        CarroDTO carro,

        BigDecimal valorTotal,

        ApoliceDTO apolice
) {

    public static ItemCarrinhoDTO from(ItemCarrinho itemCarrinho) {

        return ItemCarrinhoDTO.builder()
                .dataInicio(formatDateToString(itemCarrinho.getDataInicio()))
                .dataTermino(formatDateToString(itemCarrinho.getDataTermino()))
                .carro( CarroDTO.from(itemCarrinho.getCarro()))
                .valorTotal(itemCarrinho.getValorTotal())
                .apolice(ApoliceDTO.from(itemCarrinho.getApolice()))
                .build();
    }
}
