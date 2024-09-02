package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.AcessorioDTO;
import com.squad11.locadora.dtos.CreateAcessorioDTO;
import com.squad11.locadora.dtos.ListAcessorioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Acessórios", description = "API para gerenciamento de acessórios de carros")
@RequestMapping("/api/v1/acessorios")
public interface AcessorioController {

    @Operation(
            summary = "Listar acessórios",
            description = "Retorna uma lista de acessórios com suporte a paginação e ordenação.",
            parameters = {
                    @Parameter(name = "page", description = "Número da página para paginação", example = "0"),
                    @Parameter(name = "size", description = "Número de itens por página", example = "10"),
                    @Parameter(name = "orderBy", description = "Ordenar por ascendente ou descendente", example = "asc")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de acessórios recuperada com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ListAcessorioDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content)
            }
    )
    @GetMapping
    ResponseEntity<ListAcessorioDTO> list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy
    );

    @Operation(
            summary = "Obter detalhes de um acessório",
            description = "Retorna as informações detalhadas de um acessório com base no ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalhes do acessório recuperados com sucesso",
                            content = @Content(schema = @Schema(implementation = AcessorioDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Acessório não encontrado", content = @Content)
            }
    )
    @GetMapping("{acessorioId}")
    ResponseEntity<AcessorioDTO> show(
            @Parameter(description = "ID do acessório", required = true) @PathVariable Long acessorioId
    );


    @Operation(
            summary = "Criar um novo acessório",
            description = "Cria um novo acessório com base nas informações fornecidas.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Acessório criado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = AcessorioDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    @PostMapping
    ResponseEntity<AcessorioDTO> create(
            @RequestBody @Validated CreateAcessorioDTO createAcessorioDTO
    );
}
