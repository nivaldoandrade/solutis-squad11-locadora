package com.squad11.locadora.controllers.pessoa;

import com.squad11.locadora.dtos.aluguel.AluguelMotoristaDTO;
import com.squad11.locadora.dtos.aluguel.PedidoDTO;
import com.squad11.locadora.dtos.pessoa.MotoristaDTO;
import com.squad11.locadora.dtos.pessoa.request.CreateLinkMotoristaDTO;
import com.squad11.locadora.dtos.pessoa.request.CreateMotoristaDTO;
import com.squad11.locadora.dtos.aluguel.request.ListAlugueisMotoristaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Motoristas", description = "API para gerenciamento de motoristas")
@RequestMapping("/api/motoristas")
public interface MotoristaController {

    @Operation(
            summary = "Listar alugueis de um motorista",
            description = "Retorna uma lista de alugueis associados a um motorista, com suporte a paginação e ordenação.",
            parameters = {
                    @Parameter(name = "page", description = "Número da página para paginação", example = "0"),
                    @Parameter(name = "size", description = "Número de itens por página", example = "10"),
                    @Parameter(name = "orderBy", description = "Ordenar por ascendente ou descendente", example = "asc")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de alugueis recuperada com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = ListAlugueisMotoristaDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Motorista não encontrado", content = @Content)
            }
    )
    @GetMapping("{motoristaId}/alugueis")
    ResponseEntity<ListAlugueisMotoristaDTO> showAlugueis(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy,
            @PathVariable Long motoristaId
    );


    @Operation(
            summary = "Obter detalhes do motorista",
            description = "Retorna as informações detalhadas de um motorista com base no ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalhes do motorista recuperados com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = MotoristaDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Motorista não encontrado", content = @Content)
            }
    )
    @GetMapping("{motoristaId}")
    ResponseEntity<MotoristaDTO> show(@PathVariable Long motoristaId);


    @Operation(
            summary = "Obter detalhes de um aluguel específico de um motorista",
            description = "Retorna as informações detalhadas de um aluguel específico associado a um motorista.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalhes do aluguel recuperados com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = AluguelMotoristaDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Motorista ou aluguel não encontrado", content = @Content)
            }
    )
    @GetMapping("/aluguel/{motoristaId}/{aluguelId}")
    ResponseEntity<AluguelMotoristaDTO> showAluguel(
            @PathVariable Long motoristaId,
            @PathVariable Long aluguelId
    );

    @GetMapping("/pedido/{motoristaId}/{pedidoId}")
    ResponseEntity<PedidoDTO> showPedido(
            @PathVariable Long motoristaId,
            @PathVariable Long pedidoId
    );


    @Operation(
            summary = "Criar um novo motorista",
            description = "Cria um novo motorista com base nas informações fornecidas.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Motorista criado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = CreateLinkMotoristaDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PostMapping
    ResponseEntity<CreateLinkMotoristaDTO> create(
            @RequestBody CreateMotoristaDTO createMotoristaDTO
    );
}
