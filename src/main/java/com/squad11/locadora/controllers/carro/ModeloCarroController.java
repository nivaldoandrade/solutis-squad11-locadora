package com.squad11.locadora.controllers.carro;

import com.squad11.locadora.dtos.carro.request.CreateModeloCarroDTO;
import com.squad11.locadora.dtos.carro.request.ListModeloCarroDTO;
import com.squad11.locadora.dtos.carro.ModeloDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Modelos de Carros", description = "API para gerenciamento de modelos de carros")
@RequestMapping("/api/modelos-carros")
public interface ModeloCarroController {

    @Operation(
            summary = "Listar modelos de carros",
            description = "Retorna uma lista de modelos de carros com suporte a paginação e ordenação.",
            parameters = {
                    @Parameter(name = "page", description = "Número da página para paginação", example = "0"),
                    @Parameter(name = "size", description = "Número de itens por página", example = "10"),
                    @Parameter(name = "orderBy", description = "Ordenar por ascendente ou descendente", example = "asc")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de modelos de carros recuperada com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ListModeloCarroDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content)
            }
    )
    @GetMapping
    ResponseEntity<ListModeloCarroDTO> list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy
    );

    @Operation(
            summary = "Obter detalhes do modelo de carro",
            description = "Retorna as informações detalhadas de um modelo de carro com base no ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalhes do modelo de carro recuperados com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ModeloDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Modelo de carro não encontrado", content = @Content)
            }
    )
    @GetMapping("{modeloCarroId}")
    ResponseEntity<ModeloDTO> show(
            @Parameter(description = "ID do modelo de carro", required = true) @PathVariable Long modeloCarroId
    );

    @Operation(
            summary = "Criar um novo modelo de carro",
            description = "Cria um novo modelo de carro com base nas informações fornecidas.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Modelo de carro criado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ModeloDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PostMapping
    ResponseEntity<ModeloDTO> create(
            @RequestBody @Validated CreateModeloCarroDTO createModeloCarroDTO
    );
}
