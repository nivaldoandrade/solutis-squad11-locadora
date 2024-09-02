package com.squad11.locadora.controllers.aluguel;


import com.squad11.locadora.dtos.aluguel.AluguelDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Tag(name = "Aluguéis", description = "API para gerenciamento de aluguéis de carros")
@RequestMapping("/api/alugueis")
public interface AluguelController {

    @Operation(
            summary = "Obter detalhes de um aluguel",
            description = "Retorna as informações detalhadas de um aluguel com base no ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalhes do aluguel recuperados com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = AluguelDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Aluguel não encontrado", content = @Content)
            }
    )
    @GetMapping("{aluguelId}")
    ResponseEntity<AluguelDTO> show(
            @Parameter(description = "ID do aluguel", required = true) @PathVariable Long aluguelId
    );
}
