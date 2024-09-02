package com.squad11.locadora.controllers.aluguel;

import com.squad11.locadora.dtos.aluguel.request.CreateAluguelDTO;
import com.squad11.locadora.dtos.aluguel.PedidoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pedidos", description = "API para gerenciamento de pedidos")
@RequestMapping("/api/pedidos")
public interface PedidoController {

    @Operation(
            summary = "Obter detalhes do pedido",
            description = "Retorna as informações detalhadas de um pedido com base no ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalhes do pedido recuperados com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = PedidoDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content)
            }
    )
    @GetMapping("{pedidoId}")
    ResponseEntity<PedidoDTO> show(
            @Parameter(description = "ID do pedido", required = true) @PathVariable Long pedidoId
    );

    @Operation(
            summary = "Criar um novo pedido",
            description = "Cria um novo pedido com base no carrinho e nos detalhes do aluguel fornecidos.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Pedido criado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = PedidoDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PostMapping("{carrinhoId}")
    ResponseEntity<PedidoDTO> create(
            @Parameter(description = "ID do carrinho", required = true)  @PathVariable Long carrinhoId,
            @Parameter(description = "Detalhes do aluguel", required = true) @RequestBody @Validated CreateAluguelDTO createAluguelDTO
    );

    @Operation(
            summary = "Processar pagamento com cartão",
            description = "Processa o pagamento com cartão para um pedido específico.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pagamento processado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = PedidoDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Erro ao processar pagamento", content = @Content)
            }
    )
    @PostMapping("{pedidoId}/pagamento-cartao")
    ResponseEntity<PedidoDTO> paymentCard(
            @Parameter(description = "ID do pedido", required = true) @PathVariable Long pedidoId
    );
}
