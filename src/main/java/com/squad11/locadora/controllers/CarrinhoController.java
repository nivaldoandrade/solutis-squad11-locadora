package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.CreateCarrinhoDTO;
import com.squad11.locadora.dtos.CreateItemCarrinhoDTO;
import com.squad11.locadora.dtos.ResponseCarrinhoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Carrinhos", description = "API para gerenciamento de carrinhos de compras")
@RequestMapping("/api/v1/carrinhos")
public interface CarrinhoController {

    @Operation(
            summary = "Obter detalhes de um carrinho",
            description = "Retorna os detalhes de um carrinho com base no ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalhes do carrinho recuperados com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseCarrinhoDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Carrinho não encontrado", content = @Content)
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<ResponseCarrinhoDTO> show(
            @Parameter(description = "ID do carrinho", required = true) @PathVariable Long id
    );

    @Operation(
            summary = "Criar um novo carrinho",
            description = "Cria um novo carrinho com base nas informações fornecidas.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Carrinho criado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseCarrinhoDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PostMapping
    ResponseEntity<ResponseCarrinhoDTO> create(
            @RequestBody @Validated CreateCarrinhoDTO createCarrinhoDTO
    );



    @Operation(
            summary = "Adicionar ou atualizar um carro no carrinho",
            description = "Adiciona um carro ao carrinho ou atualiza as informações do carro já presente no carrinho.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Carrinho atualizado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseCarrinhoDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "404", description = "Carrinho ou carro não encontrado", content = @Content)
            }
    )
    @PatchMapping("{carrinhoId}")
    ResponseEntity<ResponseCarrinhoDTO> addAndUpdateCarro(
            @Parameter(description = "ID do carrinho", required = true) @PathVariable Long carrinhoId,
            @RequestBody @Validated CreateItemCarrinhoDTO carrinhoCarroDTO
    );

    @Operation(
            summary = "Remover um carro do carrinho",
            description = "Remove um carro específico do carrinho.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Carro removido com sucesso do carrinho"),
                    @ApiResponse(responseCode = "404", description = "Carrinho ou carro não encontrado")
            }
    )
    @DeleteMapping("{carrinhoId}/{carroId}")
    ResponseEntity<Void> deleteCarroCarrinho(
            @Parameter(description = "ID do carrinho", required = true) @PathVariable Long carrinhoId,
            @Parameter(description = "ID do carro", required = true) @PathVariable Long carroId
    );

    @Operation(
            summary = "Excluir um carrinho",
            description = "Remove um carrinho e todos os carros associados a ele.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Carrinho excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Carrinho não encontrado")
            }
    )
    @DeleteMapping("{carrinhoId}")
    public ResponseEntity<Void> deleteCarrinho(
            @Parameter(description = "ID do carrinho", required = true) @PathVariable Long carrinhoId
    );

}
