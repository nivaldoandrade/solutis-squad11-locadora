package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.CarroDTO;
import com.squad11.locadora.dtos.CreateCarroDTO;
import com.squad11.locadora.dtos.ListCarroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Carros", description = "API para gerenciamento de carros")
@RequestMapping("/api/v1/carros")
public interface CarroController {

    @Operation(
            summary = "Listar carros disponíveis",
            description = "Retorna uma lista de carros disponíveis com suporte a paginação, ordenação, categorias e acessórios.",
            parameters = {
                    @Parameter(name = "page", description = "Número da página para paginação", example = "0"),
                    @Parameter(name = "size", description = "Número de itens por página", example = "10"),
                    @Parameter(name = "orderBy", description = "Ordenar por ascendente ou descendente", example = "asc"),
                    @Parameter(
                            name = "categorias",
                            description = "Lista de categorias para filtrar os carros",
                            example = "[\"SEDAN_MEDIO\", \"MINIVAN\"]"
                    ),
                    @Parameter(
                            name = "acessorios",
                            description = "Lista de acessórios para filtrar os carros",
                            example = "[\"Ar condicionado\", \"Airbag\"]"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de carros disponíveis recuperada com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ListCarroDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content)
            }
    )
    @GetMapping("/disponiveis")
    ResponseEntity<ListCarroDTO> getCarrosDisponiveis(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy,
            @RequestParam(name = "categorias",required = false) List<String> categorias,
            @RequestParam(name = "acessorios", required = false) List<String> acessorios
    );

    @Operation(
            summary = "Obter detalhes de um carro",
            description = "Retorna as informações detalhadas de um carro com base no ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalhes do carro recuperados com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = CarroDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Carro não encontrado", content = @Content)
            }
    )
    @GetMapping("/disponiveis/{id}")
    ResponseEntity<CarroDTO> show(
            @Parameter(description = "ID do carro", required = true) @PathVariable Long id
    );

    @Operation(
            summary = "Criar um novo carro",
            description = "Cria um novo carro com base nas informações fornecidas.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Carro criado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = CarroDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PostMapping
    ResponseEntity<CarroDTO> create(
            @RequestBody @Validated CreateCarroDTO createCarroDTO
    );
}
