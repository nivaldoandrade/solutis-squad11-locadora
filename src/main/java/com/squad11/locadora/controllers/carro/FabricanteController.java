package com.squad11.locadora.controllers.carro;

import com.squad11.locadora.dtos.carro.request.CreateFabricanteDTO;
import com.squad11.locadora.dtos.carro.FabricanteDTO;
import com.squad11.locadora.dtos.carro.request.ListFabricanteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Fabricantes", description = "API para gerenciamento de fabricantes")
@RequestMapping("/api/fabricantes")
public interface FabricanteController {

    @Operation(
            summary = "Listar fabricantes",
            description = "Retorna uma lista de fabricantes com suporte a paginação e ordenação.",
            parameters = {
                    @Parameter(name = "page", description = "Número da página para paginação", example = "0"),
                    @Parameter(name = "size", description = "Número de itens por página", example = "10"),
                    @Parameter(name = "orderBy", description = "Ordenar por ascendente ou descendente", example = "asc")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de fabricantes recuperada com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ListFabricanteDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content)
            }
    )
    @GetMapping
    ResponseEntity<ListFabricanteDTO> list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy
    );

    @Operation(
            summary = "Obter detalhes do fabricante",
            description = "Retorna as informações detalhadas de um fabricante com base no ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalhes do fabricante recuperados com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = FabricanteDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Fabricante não encontrado", content = @Content)
            }
    )
    @GetMapping("{fabricanteId}")
    ResponseEntity<FabricanteDTO> show(@PathVariable Long fabricanteId);

    @Operation(
            summary = "Criar um novo fabricante",
            description = "Cria um novo fabricante com base nas informações fornecidas.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Fabricante criado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = FabricanteDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PostMapping
    ResponseEntity<FabricanteDTO> create(
            @RequestBody @Validated CreateFabricanteDTO createFabricanteDTO
    );
}
