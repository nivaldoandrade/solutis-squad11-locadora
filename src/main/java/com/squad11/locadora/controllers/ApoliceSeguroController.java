package com.squad11.locadora.controllers;

import com.squad11.locadora.dtos.ApoliceDTO;
import com.squad11.locadora.dtos.CreateApoliceSeguroDTO;
import com.squad11.locadora.dtos.ListApoliceSeguroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Apólices de Seguro", description = "API para gerenciamento de apólices de seguro")
@RequestMapping("/api/v1/apolices-seguros")
public interface ApoliceSeguroController {


    @Operation(
            summary = "Listar apólices de seguro",
            description = "Retorna uma lista de apólices de seguro com suporte a paginação e ordenação.",
            parameters = {
                    @Parameter(name = "page", description = "Número da página para paginação", example = "0"),
                    @Parameter(name = "size", description = "Número de itens por página", example = "10"),
                    @Parameter(name = "orderBy", description = "Ordenar por ascendente ou descendente", example = "asc")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de apólices de seguro recuperada com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ListApoliceSeguroDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content)
            }
    )
    @GetMapping
    ResponseEntity<ListApoliceSeguroDTO> list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy
    );

    @Operation(
            summary = "Obter detalhes de uma apólice de seguro",
            description = "Retorna as informações detalhadas de uma apólice de seguro com base no ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalhes da apólice de seguro recuperados com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ApoliceDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Apólice de seguro não encontrada", content = @Content)
            }
    )
    @GetMapping("{apoliceSeguroId}")
    ResponseEntity<ApoliceDTO> show(
            @Parameter(description = "ID da apólice de seguro", required = true) @PathVariable Long apoliceSeguroId
    );


    @Operation(
            summary = "Criar uma nova apólice de seguro",
            description = "Cria uma nova apólice de seguro com base nas informações fornecidas.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Apólice de seguro criada com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ApoliceDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PostMapping
    ResponseEntity<ApoliceDTO> create(
            @RequestBody @Validated CreateApoliceSeguroDTO createApoliceSeguroDTO
    );

}
